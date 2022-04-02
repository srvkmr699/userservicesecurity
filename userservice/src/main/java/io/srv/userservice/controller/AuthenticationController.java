package io.srv.userservice.controller;

import io.srv.userservice.dto.request.AuthRequestDTO;
import io.srv.userservice.dto.response.AuthResponseDTO;
import io.srv.userservice.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponseDTO> createJwtToken(@Valid @RequestBody AuthRequestDTO authRequestDTO) throws Exception {
        return new ResponseEntity<>(authenticationService.authenticate(authRequestDTO), HttpStatus.OK);
    }
}
