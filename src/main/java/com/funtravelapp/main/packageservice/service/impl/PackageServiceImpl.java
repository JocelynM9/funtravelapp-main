package com.funtravelapp.main.packageservice.service.impl;

import com.funtravelapp.main.packageservice.dto.PackageInputDTO;
import com.funtravelapp.main.packageservice.entity.Package;
import com.funtravelapp.main.packageservice.repository.PackageRepository;
import com.funtravelapp.main.packageservice.service.PackageService;
import com.funtravelapp.main.tokenAuth.dto.GetUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Package insertNewPackage(String authorizationHeader, PackageInputDTO packageInputDTO) throws Exception {
        if(packageInputDTO.getName().isBlank()){
            throw new Exception("Please input package name");
        }

        Package aPackage = new Package();
        aPackage.setId(0);
        aPackage.setUserId(packageInputDTO.getUserId());
        aPackage.setName(packageInputDTO.getName());
        aPackage.setDescription(packageInputDTO.getDescription());
        aPackage.setPrice(packageInputDTO.getPrice());

        return repository.save(aPackage);
    }

    @Override
    public List<Package> allPackages() {
        List<Package> packages;
        packages = repository.findAll();
//        if (userDTO.getRole().equalsIgnoreCase("customer")){
//            packages = repository.findAll();
//        }else {
//            packages = repository.findByUserId(userDTO.getId());
//        }
        return packages;
    }


    @Override
    public List<Package> allPackageBySellerId(int sellerId) {
        return repository.findByUserId(sellerId);
    }

    @Override
    public String uploadImage(MultipartFile file, Integer packageId) throws IOException {
        Optional<Package> getPackage = repository.findById(packageId);

        if(getPackage.isEmpty()){
            throw new IOException("400");
        }
        Package aPackage = getPackage.get();

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
    public ResponseEntity<?> getPackageById(Integer packageId) {
        return new ResponseEntity<>(repository.findPackageById(packageId), HttpStatus.OK );
    }

    @Override
    public ResponseEntity<String> delete(Integer id) {
        Optional<Package> getPackage = repository.findById(id);

        if(getPackage.isEmpty()){
            return new ResponseEntity<>("400", null);
        }
        Package aPackage = getPackage.get();

        if(aPackage.getImage() != null){
            File existingFile = new File(folder + aPackage.getImage());
            existingFile.delete();
        }
        repository.delete(aPackage);

        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }

    @Override
    public Package updatePackage(PackageInputDTO dto, Integer id) throws Exception {
        if(dto.getName().isBlank()){
            throw new Exception("Please input package name");
        }

        Package aPackage = new Package();
        aPackage.setId(id);
        aPackage.setUserId(dto.getUserId());
        aPackage.setName(dto.getName());
        aPackage.setImage(dto.getImage());
        aPackage.setDescription(dto.getDescription());
        aPackage.setPrice(dto.getPrice());

        return repository.save(aPackage);
    }

    ;
}
