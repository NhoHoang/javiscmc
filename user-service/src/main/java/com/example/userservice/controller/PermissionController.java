package com.example.userservice.controller;

import com.example.springclass2.entity.Permission;
import com.example.springclass2.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/permission")

public class PermissionController {
    @Autowired
    private PermissionService service;

    @GetMapping()
    public ResponseEntity<?> getAllPermission() {
        ArrayList<Permission> entities = service.getAllPermission();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getAllPermissionFilter(@RequestHeader(name="permissionName", required = false) String permissionName) {
        Permission entities = service.getAllPermissionFilter(permissionName);
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }
}
