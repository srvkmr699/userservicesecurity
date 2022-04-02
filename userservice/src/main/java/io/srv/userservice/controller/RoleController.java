package io.srv.userservice.controller;

import io.srv.userservice.domain.Role;
import io.srv.userservice.dto.request.AddRoleToUserDTO;
import io.srv.userservice.dto.request.RoleRequestDTO;
import io.srv.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Role> saveRole(@RequestBody RoleRequestDTO roleRequestDTO) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/create").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(roleRequestDTO));
    }

    @PostMapping("/assign")
    public ResponseEntity<Role> saveRole(@RequestBody AddRoleToUserDTO addRoleToUserDTO) {
        userService.addRoleToUser(addRoleToUserDTO.getUserName(), addRoleToUserDTO.getRoleName());
        return ResponseEntity.ok().build();
    }

}
