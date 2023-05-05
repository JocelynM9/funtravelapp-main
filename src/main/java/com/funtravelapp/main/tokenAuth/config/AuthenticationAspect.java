package com.funtravelapp.main.tokenAuth.config;

import com.funtravelapp.main.authenticationservice.entity.User;
import com.funtravelapp.main.authenticationservice.entity.UserToken;
import com.funtravelapp.main.authenticationservice.repository.UserRepository;
import com.funtravelapp.main.authenticationservice.repository.UserTokenRepository;
import com.funtravelapp.main.tokenAuth.dto.GetTokenResponse;
import com.funtravelapp.main.tokenAuth.dto.GetUserDTO;
import com.funtravelapp.main.tokenAuth.validator.StringValidator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Aspect
@Component
public class AuthenticationAspect {

    @Autowired
    StringValidator stringValidator;

    @Autowired
    UserTokenRepository userTokenRepository;

    @Autowired
    UserRepository userRepository;

    @Pointcut("execution(* com.funtravelapp.main.packageservice.service.impl.PackageServiceImpl.*(..))")
    private void packageServiceAuth(){}

    @Pointcut("execution(* com.funtravelapp.main.cartservice.service.impl.CartServiceImpl.*(..))")
    private void cartServiceAuth(){}

    @Pointcut("execution(* com.funtravelapp.main.cartservice.service.impl.KafkaServiceImpl.*(..))")
    private void kafkaServiceAuth(){}

    @Around("packageServiceAuth() || cartServiceAuth() || kafkaServiceAuth()")
    public Object checkToken(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();

        if (args.length < 3){
            return pjp.proceed();
        }

        String authHeader = (String) args[0];
        boolean isValid = stringValidator.setStr(authHeader).isValid();
        System.out.println(authHeader);
        if (!isValid) {
            throw new Exception("Unauthorized, invalid token");
        }

        String token = authHeader.split(" ")[1];
        System.out.println("Token : " + token);

        Optional<UserToken> userToken = userTokenRepository.findByToken(token);

        if(userToken.isEmpty()){
            throw new Exception("Unauthorized, invalid token");
        }

        UserToken ut = userToken.get();

        Optional<User> findUser = userRepository.findById(ut.getUserId());

        if(findUser.isEmpty()){
            throw new Exception("User not found!");
        }

        User u = findUser.get();

        Map<String, Boolean> allowedUser = (Map<String, Boolean>) args[1];

        if (!allowedUser.get(u.getRole().toLowerCase())){
            throw new Exception("Unauthorized, user not allowed!");
        }

        args[2] = u;

        return pjp.proceed(args);
    }
}
