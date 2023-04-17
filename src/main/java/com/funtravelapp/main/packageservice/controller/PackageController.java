package com.funtravelapp.main.packageservice.controller;

import com.funtravelapp.main.packageservice.entity.Package;
import com.funtravelapp.main.packageservice.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("package")
public class PackageController {
    @Autowired
    PackageService packageService;

    @GetMapping("/all")
    public @ResponseBody List<Package> getAll(){
        return packageService.allPackages();
    }
}
