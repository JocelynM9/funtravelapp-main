package com.funtravelapp.main.packageservice.service.impl;

import com.funtravelapp.main.packageservice.dto.PackageInputDTO;
import com.funtravelapp.main.packageservice.entity.Package;
import com.funtravelapp.main.packageservice.repository.PackageRepository;
import com.funtravelapp.main.packageservice.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {
    @Autowired
    PackageRepository repository;

    @Override
    public ResponseEntity<String> insertNewPackage(PackageInputDTO packageInputDTO) {
        if(packageInputDTO.getName().isBlank()){
            return new ResponseEntity<String>("Please input package name", HttpStatus.BAD_REQUEST);
        }

        Package aPackage = new Package();
        aPackage.setId(0);
        aPackage.setUserId(packageInputDTO.getUserId());
        aPackage.setName(packageInputDTO.getName());
        aPackage.setImage(packageInputDTO.getImage());
        aPackage.setDescription(packageInputDTO.getDescription());
        aPackage.setPrice(packageInputDTO.getPrice());
        repository.save(aPackage);

        return new ResponseEntity<>("Successfully insert new package!", HttpStatus.OK);
    }

    public List<Package> allPackages(){
        return repository.findAll();
    }


    @Override
    public List<Package> allPackageBySellerId(int sellerId) {
        return repository.findAllByUserId(sellerId);
    }

    @Override
    public ResponseEntity<?> uploadImage(MultipartFile file) throws IOException {
        String folder = "./src/main/resources/images/";
        String fileName = file.getOriginalFilename();
        Path path = Paths.get(folder + fileName);
        Files.write(path, file.getBytes());

        return new ResponseEntity<>("Success upload " + fileName, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getPackageById(int packageId) {
        return new ResponseEntity<>(repository.findPackageById(packageId), HttpStatus.OK );
    }

    @Override
    public ResponseEntity<String> delete(int id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updatePackage(PackageInputDTO dto, int id) {
        if(dto.getName().isBlank()){
            return new ResponseEntity<String>("Please input package name", HttpStatus.BAD_REQUEST);
        }

        Package aPackage = new Package();
        aPackage.setId(id);
        aPackage.setUserId(dto.getUserId());
        aPackage.setName(dto.getName());
        aPackage.setImage(dto.getImage());
        aPackage.setDescription(dto.getDescription());
        aPackage.setPrice(dto.getPrice());
        repository.save(aPackage);

        return new ResponseEntity<>("Successfully updated package!", HttpStatus.OK);
    }

    ;
}
