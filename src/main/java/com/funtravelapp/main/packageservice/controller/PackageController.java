package com.funtravelapp.main.packageservice.controller;

import com.funtravelapp.main.packageservice.dto.PackageInputDTO;
import com.funtravelapp.main.packageservice.service.PackageService;
import com.funtravelapp.main.responseMapper.ResponseMapper;
import com.funtravelapp.main.tokenAuth.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/package")
public class  PackageController {

    @Autowired
    PackageService packageService;
    @Autowired
    RoleService roleService;

    @PostMapping("/insert")
    public ResponseEntity<?> newPackage(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody PackageInputDTO dto){
        try {
            return ResponseMapper.ok(null, packageService.insertNewPackage(authorizationHeader,dto));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), null);
        }
    }

    @PostMapping("/uploadImage/{id}")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file,
                                         @PathVariable("id") Integer packageId) throws IOException {
        try{
            System.out.println("File: "+ file.getOriginalFilename());
            return ResponseMapper.ok(null, packageService.uploadImage(file, packageId));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), null);
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePackage(@RequestBody PackageInputDTO dto,
                                           @PathVariable("id") Integer id){
        try {
            return ResponseMapper.ok(null, packageService.updatePackage(dto, id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), null);
        }
    }

    @GetMapping("/all")
    public @ResponseBody ResponseEntity<?> getAllPackages(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader){
        try{
            return ResponseMapper.ok(null, packageService.allPackages());
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), null);
        }
    }

    @GetMapping("/all-by-id/{id}")
    public @ResponseBody ResponseEntity<?> getAllByUserId(@PathVariable("id") Integer id){
        try {
            return ResponseMapper.ok(null, packageService.allPackageBySellerId(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), null);
        }

    }

    @GetMapping("/get-package-by-id/{id}")
    public ResponseEntity<?> getPackageById(@PathVariable("id") Integer id){
        try{
            return ResponseMapper.ok(null, packageService.getPackageById(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        try{
            packageService.delete(id);
            return ResponseMapper.ok(null, null);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseMapper.badRequest(e.getMessage(), null);
        }

    }


}
