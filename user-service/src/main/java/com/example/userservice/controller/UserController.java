package com.example.userservice.controller;

import com.example.springclass2.common.CustomResponse;
import com.example.springclass2.common.HttpStatusConstants;
import com.example.springclass2.dto.UserDTO;
import com.example.springclass2.dto.UserUpdateDTO;
import com.example.springclass2.entity.Permission;
import com.example.springclass2.entity.User;
import com.example.springclass2.exception.BussinessException;
import com.example.springclass2.service.PermissionService;
import com.example.springclass2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    private final PermissionService permissionService;

    @GetMapping()
    public CustomResponse<Object> getAllUser(@RequestHeader(name = "x-permissionName", required = false) String permissionName,
                                             @PageableDefault() Pageable pageable) {
        Permission response = permissionService.getAllPermissionFilter(permissionName);

        List<User> entities = service.getAllUser(response, pageable);
        Page<User> userPage = new PageImpl<User>(entities, pageable, entities.size());
        return new CustomResponse<>(HttpStatusConstants.SUCCESS_CODE, HttpStatusConstants.SUCCESS_MESSAGE, userPage);
    }

    @GetMapping(value = "/h2")
    public CustomResponse<Object> getAllUser2 (Pageable pageable){
        Page<User> userPage = service.getAllUser2(pageable);
        return new CustomResponse<>(HttpStatusConstants.SUCCESS_CODE, HttpStatusConstants.SUCCESS_MESSAGE, userPage);
    }

    @PostMapping("/create")
    public CustomResponse<Object> createUser(@RequestBody UserDTO dto) throws BussinessException {
        return service.createUser(dto);
    }

    @PutMapping("/update")
    public CustomResponse<Object> updateUser(@RequestBody UserUpdateDTO dto) throws BussinessException {
        return service.updateUser(dto);
    }

    @DeleteMapping("/delete")
    public CustomResponse<Object> deleteUser(@RequestParam int userId) throws BussinessException {
        return service.deleteUser(userId);
    }
}
