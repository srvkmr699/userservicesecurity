package io.srv.userservice.service;

import io.srv.userservice.domain.Role;
import io.srv.userservice.domain.User;
import io.srv.userservice.dto.request.RoleRequestDTO;

import java.util.List;

/**
 * UserService is to handle all business logic to for user management.
 */
public interface UserService {

    /**
     * This method is to save user into db.
     *
     * @param user
     * @return User
     */
    User saveUser(User user);

    /**
     * This method is to save role into db.
     * @param role
     * @return
     */
    Role saveRole(RoleRequestDTO roleRequestDTO);

    /**
     * This method is to assign any specific role to any specific user.
     *
     * @param userName
     * @param roleName
     */
    void addRoleToUser(String userName, String roleName);

    /**
     * This method is to get single user.
     *
     * @return
     */
    User getUser(String userName);

    /**
     * This method is to get multiple users from db.
     *
     * @return
     */
    List<User> getMultipleUsers();
}
