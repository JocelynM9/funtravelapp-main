package com.funtravelapp.main.packageservice.controller;

import com.funtravelapp.main.packageservice.dto.PackageInputDTO;
import com.funtravelapp.main.packageservice.entity.Package;
import com.funtravelapp.main.packageservice.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/package")
public class PackageController {

    @Autowired
    PackageService packageService;

    @PostMapping("/insert")
    public ResponseEntity<?> newPackage(@RequestBody PackageInputDTO dto){
        return packageService.insertNewPackage(dto);
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
        return packageService.uploadImage(file);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePackage(@RequestBody PackageInputDTO dto,
                                           @PathVariable("id") int id){
        return packageService.updatePackage(dto, id);
    }
//    @PutMapping("/updateImage")

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<?> getAllPackages(){
        if(packageService.allPackages().isEmpty()){
            return new ResponseEntity<>("No packages!", HttpStatus.OK);
        }

        return new ResponseEntity(packageService.allPackages(), HttpStatus.OK);
    }

    @GetMapping("/all-by-id/{id}")
    public @ResponseBody ResponseEntity<?> getAllByUserId(@PathVariable("id") int id){
        List<Package> list = packageService.allPackageBySellerId(id);
        if(list.isEmpty()){
            return new ResponseEntity<>("No packages!", HttpStatus.OK);
        }
        return new ResponseEntity<>( list, HttpStatus.OK);

    }

    @GetMapping("/get-package-by-id/{id}")
    public ResponseEntity<?> getPackageById(@PathVariable("id") int id){
        return packageService.getPackageById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        return packageService.delete(id);
    }

//    @DeleteMapping("/deleteImage")
}
