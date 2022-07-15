package com.example.userservice.controller;

import com.example.springclass2.entity.UserPermission;
import com.example.springclass2.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/user-permission")

public class UserPermissionController {
    @Autowired
    private UserPermissionService service;

    @GetMapping()
    public ResponseEntity<?> getAllUserPermission() {
        ArrayList<UserPermission> entities = service.getAllUserPermission();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }
}
