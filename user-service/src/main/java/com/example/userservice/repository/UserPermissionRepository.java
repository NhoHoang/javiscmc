package com.example.userservice.repository;

import com.example.springclass2.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPermissionRepository extends JpaRepository<UserPermission, Integer> {
    @Query("select up from UserPermission up where up.userId = :userId and up.permissionId =:permissionId")
    UserPermission getUserPermission(int userId, int permissionId);

    @Query("select up from UserPermission up where up.userId = :userId")
    List<UserPermission> getUserPermissionByUserId(int userId);
}

