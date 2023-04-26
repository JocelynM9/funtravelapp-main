package com.funtravelapp.main.packageservice.service;

import com.funtravelapp.main.packageservice.dto.PackageInputDTO;
import com.funtravelapp.main.packageservice.entity.Package;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PackageService {
    public ResponseEntity<String> insertNewPackage(PackageInputDTO packageInputDTO);

    public List<Package> allPackages();

    public List<Package> allPackageBySellerId(int sellerId);

    public ResponseEntity<?> uploadImage(MultipartFile file) throws IOException;

    public ResponseEntity<?> getPackageById(int packageId);

    ResponseEntity<String> delete(int id);

    ResponseEntity<?> updatePackage(PackageInputDTO dto, int id);
}
