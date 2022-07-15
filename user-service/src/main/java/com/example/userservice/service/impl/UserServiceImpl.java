package com.example.userservice.service.impl;

import com.example.springclass2.common.CustomResponse;
import com.example.springclass2.common.HttpStatusConstants;
import com.example.springclass2.config.PropertiesConfig;
import com.example.springclass2.dto.UserDTO;
import com.example.springclass2.dto.UserUpdateDTO;
import com.example.springclass2.entity.Permission;
import com.example.springclass2.entity.User;
import com.example.springclass2.entity.UserPermission;
import com.example.springclass2.exception.BussinessException;
import com.example.springclass2.repository.PermissionRepository;
import com.example.springclass2.repository.UserPermissionRepository;
import com.example.springclass2.repository.UserRepository;
import com.example.springclass2.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
//@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserPermissionRepository userPermissionRepository;
//    @Value("${project.variable}")
//    private String projectVariable;
    @Autowired
    private PropertiesConfig propertiesConfig;
    @Autowired
    private  Environment env;

    @Override
    public List<User> getAllUser(Permission permissionName, Pageable pageable) {
//        log.info("Read properties from application.properties method 1: {}", projectVariable);
//        log.info("Read properties from application.properties method 2: {}", propertiesConfig.getVariable());
//        log.info("Read environment variables : {}", env.getActiveProfiles());
//        log.info("Read environment variables OS : {}", env.getProperty("JAVA_HOME"));
//        log.info("Read environment variables: {}", env.getProperty("project.variable"));
        return permissionName == null ? repository.findAll(pageable).getContent() :
                repository.findByPermissions(permissionName, pageable);
    }


    @Override
    public CustomResponse<Object> createUser(UserDTO dto) throws BussinessException {
        Optional<Permission> permission = permissionRepository.getPermissionByPermissionName(dto.getPermission());
        if (permission.isEmpty()) {
            return CustomResponse.error(HttpStatusConstants.FAILURE_CODE, "No have this permission");
//           throw new BussinessException(HttpStatusConstants.FAILURE_CODE, "No have this permission");
        }
        // check duplicate name of user?
        Optional<User> user = Optional.ofNullable((repository.getUserByName(dto.getName())));
        if (user == null) {
            User user1 = new User(dto.getName());
            repository.save(user1);

            // Tạo record mới trong bảng User Permission
            UserPermission userPermission = new UserPermission(user1.getId(), permission.get().getId());
            userPermissionRepository.save(userPermission);
            user = Optional.ofNullable(user1);
        } else {
            // check duplication in user permission?
            UserPermission userPermission = userPermissionRepository.getUserPermission(user.get().getId(), permission.get().getId());
            if (userPermission == null) {
                UserPermission userPermission1 = new UserPermission(user.get().getId(), permission.get().getId());
                userPermissionRepository.save(userPermission1);
            } else {
                return CustomResponse.error(HttpStatusConstants.FAILURE_CODE, "Exist this record!");
//                throw new BussinessException("500","Exist this record!");
            }
        }
        return CustomResponse.ok(user);
    }

    @Override
    public CustomResponse<Object> updateUser(UserUpdateDTO dto) throws BussinessException {
        // Check exist user in db
        Optional<User> user = Optional.ofNullable((repository.getUserByName(dto.getName())));
        if (user == null) {
            return CustomResponse.error(HttpStatusConstants.FAILURE_CODE, "No exist this user!");
//            throw new BussinessException("500","No exist this user!");
        } else {
            Optional<Permission> oldPermission = permissionRepository.getPermissionByPermissionName(dto.getOldPermission());
            Optional<Permission> newPermission = permissionRepository.getPermissionByPermissionName(dto.getNewPermission());
            // Check validation permission
            if (oldPermission.isEmpty() || newPermission.isEmpty()) {
                return CustomResponse.error(HttpStatusConstants.FAILURE_CODE, "Permission invalid");
//                throw new BussinessException("500","Permission invalid");
            }

            // Check user - old permission exist or not
            UserPermission userPermission = userPermissionRepository.getUserPermission(user.get().getId(), oldPermission.get().getId());
            if (userPermission == null) {
                return CustomResponse.error(HttpStatusConstants.FAILURE_CODE, "This user does not have this permission before!");
//                throw new BussinessException("500","This user does not have this permission before!");
            }

            // Check duplicate user - new permission
            // Can be duplicate with itself ( dto equal repo)
            UserPermission userPermissionCheck = userPermissionRepository.getUserPermission(user.get().getId(), newPermission.get().getId());
            UserPermission userPermissionDto = new UserPermission(user.get().getId(), newPermission.get().getId());
            if (userPermissionCheck != null && !userPermissionCheck.equals(userPermissionDto)) {
                return CustomResponse.error(HttpStatusConstants.FAILURE_CODE, "This user already has this permission before!");
//                throw new BussinessException("500","This user already has this permission before!");
            }
            userPermission.setPermissionId(newPermission.get().getId());
            System.out.println(userPermission);
            userPermissionRepository.save(userPermission);
        }
        return CustomResponse.ok(user);
    }

    @Override
    @Transactional
    public CustomResponse<Object> deleteUser(int userId) throws BussinessException {
        Optional<User> user = repository.findById(userId);

        if (user.isEmpty()) {
            return CustomResponse.error(HttpStatusConstants.FAILURE_CODE, "No exist this user!");
//            throw new BussinessException("500","No exist this user!");
        } else {
            List<UserPermission> userPermissionList = userPermissionRepository.getUserPermissionByUserId(userId);
            for (UserPermission userPermission : userPermissionList) {
                userPermissionRepository.delete(userPermission);
            }
            repository.deleteUser(userId);
            return CustomResponse.ok("Delete success");
        }

    }

    @Override
    public Page<User> getAllUser2(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
