package com.example.userservice.service;

import com.example.springclass2.entity.Permission;

import java.util.ArrayList;

public interface PermissionService {
    ArrayList<Permission> getAllPermission();

    Permission getAllPermissionFilter(String permissionName);
}
