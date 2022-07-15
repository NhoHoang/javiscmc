package com.example.userservice.repository;

import com.example.springclass2.entity.Permission;
import com.example.springclass2.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    ArrayList<User> findByPermissions(Permission permissionName, Pageable pageable);

    User getUserByName(String name);

    Object getUserById(int userId);

    @Modifying
    @Query("DELETE FROM User u WHERE u.id =:userId")
    void deleteUser(int userId);
}
