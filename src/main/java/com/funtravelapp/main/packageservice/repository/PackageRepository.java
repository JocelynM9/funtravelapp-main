package com.funtravelapp.main.packageservice.repository;

import com.funtravelapp.main.packageservice.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Integer> {

}
