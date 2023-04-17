package com.funtravelapp.main.packageservice.service;

import com.funtravelapp.main.packageservice.entity.Package;
import com.funtravelapp.main.packageservice.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageService {

    @Autowired
    PackageRepository repository;

    public List<Package> allPackages(){
      return repository.findAll();
    };

}
