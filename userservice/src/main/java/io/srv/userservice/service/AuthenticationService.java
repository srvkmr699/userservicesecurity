package io.srv.userservice.service;

import io.srv.userservice.dto.request.AuthRequestDTO;
import io.srv.userservice.dto.response.AuthResponseDTO;
import io.srv.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Lazy
public class AuthenticationService {

    private final AuthUserDetailsService authUserDetailsService;

    private final AuthenticationManager authenticationManager;

    public AuthResponseDTO authenticate(AuthRequestDTO authRequestDTO) throws Exception {
        authenticate(authRequestDTO.getUserName(), authRequestDTO.getPassword());
        UserDetails userDetails = authUserDetailsService.loadUserByUsername(authRequestDTO.getUserName());
        return JwtUtil.generateToken(userDetails);
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
