package com.funtravelapp.main.repository;

import com.funtravelapp.main.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package, Integer> {

}
