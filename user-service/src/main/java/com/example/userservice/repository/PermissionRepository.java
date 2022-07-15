package com.example.userservice.repository;

import com.example.springclass2.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Object findByPermissionName(String permissionName);

    Optional<Permission> getPermissionByPermissionName(String permission);
}
