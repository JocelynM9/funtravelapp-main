package com.funtravelapp.main.packageservice.repository;

import com.funtravelapp.main.packageservice.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer> {


    Package findPackageById(Integer packageId);

    List<Package> findByUserId(Integer id);

}
