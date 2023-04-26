package com.funtravelapp.main.packageservice.repository;

import com.funtravelapp.main.packageservice.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends JpaRepository<Package, Integer> {
    @Query("SELECT p FROM Package p WHERE p.userId = ?1")
    List<Package> findAllByUserId(Integer userId);

    Package findPackageById(int packageId);
}
