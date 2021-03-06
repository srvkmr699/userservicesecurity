package io.srv.userservice.service;

import io.srv.userservice.domain.Role;
import io.srv.userservice.domain.User;
import io.srv.userservice.dto.request.RoleRequestDTO;
import io.srv.userservice.repository.RoleRepository;
import io.srv.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        log.info("saving user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(RoleRequestDTO roleRequestDTO) {
        log.info("saving role {} to the database", roleRequestDTO.getName());
        Role role = new Role();
        role.setName(roleRequestDTO.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("assigning role {} to user {}", roleName, userName);
        User user = userRepository.findByUserName(userName);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String userName) {
        log.info("fetching user from the database with userName {}", userName);
        User user = userRepository.findByUserName(userName);
        if(Objects.isNull(user)) {
            log.error("User not found in db");
            throw new RuntimeException("User not found");
        } else {
            log.error("User found in db");
        }
        return user;
    }

    @Override
    public List<User> getMultipleUsers() {
        log.info("fetching all users from database");
        return userRepository.findAll();
    }

}
