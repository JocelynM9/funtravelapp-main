package com.funtravelapp.main.packageservice.service.impl;

import com.funtravelapp.main.authenticationservice.entity.User;
import com.funtravelapp.main.packageservice.dto.PackageInputDTO;
import com.funtravelapp.main.packageservice.entity.Package;
import com.funtravelapp.main.packageservice.repository.PackageRepository;
import com.funtravelapp.main.packageservice.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class PackageServiceImpl implements PackageService {
    @Autowired
    PackageRepository repository;

    private final String folder = "./src/main/resources/images/";


    @Override
    public Package insertNewPackage(String authorizationHeader, Map<String, Boolean> role, User user, PackageInputDTO packageInputDTO) throws Exception {
        if(packageInputDTO.getName().isBlank() || packageInputDTO.getDescription().isBlank() || packageInputDTO.getPrice().equals(0)){
            throw new Exception("Please input blank fields!");
        }

        if (user.getRole().equalsIgnoreCase("customer")){
            throw new Exception("Access denied!");
        }

        Package aPackage = new Package();
        aPackage.setId(0);
        aPackage.setUserId(user.getId());
        aPackage.setName(packageInputDTO.getName());
        aPackage.setDescription(packageInputDTO.getDescription());
        aPackage.setPrice(packageInputDTO.getPrice());

        return repository.save(aPackage);
    }

    @Override
    public List<Package> allPackages(String authorizationHeader, Map<String, Boolean> role, User user) {
        List<Package> packages;
        System.out.println(user);
        if (user.getRole().equalsIgnoreCase("customer")){
            packages = repository.findAll();
        }else {
            packages = repository.findByUserId(user.getId());
        }
        return packages;
    }


//    @Override
//    public List<Package> allPackageBySellerId(int sellerId) {
//        return repository.findByUserId(sellerId);
//    }

    @Override
    public String uploadImage(String authorizationHeader, Map<String, Boolean> role, User user, Integer packageId, MultipartFile file) throws Exception {
        if (user.getRole().equalsIgnoreCase("customer")){
            throw new Exception("Access denied!");
        }

        Optional<Package> getPackage = repository.findById(packageId);

        if(getPackage.isEmpty()){
            throw new IOException("400");
        }
        Package aPackage = getPackage.get();

        if(!aPackage.getUserId().equals(user.getId())){
            throw new Exception("Not allowed to access!");
        }

        if(aPackage.getImage() != null){
            File existingFile = new File(folder + aPackage.getImage());
            existingFile.delete();
        }

        String[] fileName = file.getOriginalFilename().split("\\.");
        if(!fileName[1].equalsIgnoreCase("jpg")
        && !fileName[1].equalsIgnoreCase("png")
        && !fileName[1].equalsIgnoreCase("jpeg")){
            throw new IOException("400", null);
        }

        String uid = UUID.randomUUID().toString();
        String newFileName = uid + "." + fileName[1];
        Path path = Paths.get(folder + newFileName);
        Files.write(path, file.getBytes());
        aPackage.setImage(newFileName);
        repository.save(aPackage);

        return "Success upload " + newFileName;
    }

    @Override
    public Package getPackageById(String authorizationHeader, Map<String, Boolean> role, User user, Integer packageId) {

        return repository.findPackageById(packageId);
    }

    @Override
    public void delete(String authorizationHeader, Map<String, Boolean> role, User user, Integer id) throws Exception {

        if (user.getRole().equalsIgnoreCase("customer")){
            throw new Exception("Access denied!");
        }

        Optional<Package> getPackage = repository.findById(id);

        if(getPackage.isEmpty()){
            throw new Exception("No package!");
        }
        Package aPackage = getPackage.get();

        if(!aPackage.getUserId().equals(user.getId())){
            throw new Exception("Not allowed to delete!");
        }

        if(aPackage.getImage() != null){
            File existingFile = new File(folder + aPackage.getImage());
            existingFile.delete();
        }
        repository.delete(aPackage);
    }

    @Override
    public Package updatePackage(String authorizationHeader, Map<String, Boolean> role, User user, Integer id, PackageInputDTO dto) throws Exception {
        if(dto.getName().isBlank()){
            throw new Exception("Please input package name");
        }

        if (user.getRole().equalsIgnoreCase("customer")){
            throw new Exception("Access denied!");
        }

        Optional<Package> findPackage = repository.findById(id);

        if(findPackage.isEmpty()){
            throw new Exception("Not found!");
        }

        Package aPackage = findPackage.get();

        if(!aPackage.getUserId().equals(user.getId())){
            throw new Exception("Not allowed to edit!");
        }

        aPackage.setName(dto.getName());
        aPackage.setDescription(dto.getDescription());
        aPackage.setPrice(dto.getPrice());

        return repository.save(aPackage);
    }

    ;
}
