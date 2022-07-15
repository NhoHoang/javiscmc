package com.example.userservice.service;

import com.example.springclass2.common.CustomResponse;
import com.example.springclass2.dto.UserDTO;
import com.example.springclass2.dto.UserUpdateDTO;
import com.example.springclass2.entity.Permission;
import com.example.springclass2.entity.User;
import com.example.springclass2.exception.BussinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<User> getAllUser(Permission permissionName, Pageable page);

    CustomResponse<Object> createUser(UserDTO dto) throws BussinessException;

    CustomResponse<Object>updateUser(UserUpdateDTO dto) throws BussinessException;

    CustomResponse<Object> deleteUser(int userId) throws BussinessException;

    Page<User> getAllUser2(Pageable pageable);
}
