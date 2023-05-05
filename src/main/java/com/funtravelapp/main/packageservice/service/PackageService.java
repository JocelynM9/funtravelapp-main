package com.funtravelapp.main.packageservice.service;

import com.funtravelapp.main.authenticationservice.entity.User;
import com.funtravelapp.main.packageservice.dto.PackageInputDTO;
import com.funtravelapp.main.packageservice.entity.Package;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PackageService {
    public Package insertNewPackage(String authorizationHeader, Map<String, Boolean> role, User user, PackageInputDTO packageInputDTO) throws Exception;

    public List<Package> allPackages(String authorizationHeader, Map<String, Boolean> role, User user);

//    public List<Package> allPackageBySellerId(int sellerId);

    public String uploadImage(String authorizationHeader, Map<String, Boolean> role, User user, Integer packageId, MultipartFile file) throws Exception;


    public Package getPackageById(String authorizationHeader, Map<String, Boolean> role, User user, Integer packageId);

    void delete(String authorizationHeader, Map<String, Boolean> role, User user, Integer id) throws Exception;

    Package updatePackage(String authorizationHeader, Map<String, Boolean> role, User user, Integer id, PackageInputDTO dto) throws Exception;
}
