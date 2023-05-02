package com.funtravelapp.main.packageservice.service;

import com.funtravelapp.main.packageservice.dto.PackageInputDTO;
import com.funtravelapp.main.packageservice.entity.Package;
import com.funtravelapp.main.tokenAuth.dto.GetUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface PackageService {
    public Package insertNewPackage(String authorizationHeader,  PackageInputDTO packageInputDTO) throws Exception;

    public List<Package> allPackages(String authorizationHeader, Map<String, Boolean> roles, GetUserDTO userDTO);

    public List<Package> allPackageBySellerId(int sellerId);

    public String uploadImage(MultipartFile file, Integer packageId) throws IOException;


    public ResponseEntity<?> getPackageById(Integer packageId);

    ResponseEntity<String> delete(Integer id);

    Package updatePackage(PackageInputDTO dto, Integer id) throws Exception;
}
