package io.srv.userservice.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.srv.userservice.dto.response.AuthResponseDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.stream.Collectors;

public class JwtUtil {

    public static AuthResponseDTO generateToken(UserDetails userDetails) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        //algorithm to create signature
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

        //create access token
        String accessToken = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer("/api/account/v1/authenticate")
                .withClaim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm); //signing the token

        //create refresh token
        String refreshToken = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer("/api/account/v1/authenticate")
                .sign(algorithm); //signing the token

        authResponseDTO.setAccessToken(accessToken);
        authResponseDTO.setRefreshToken(refreshToken);
        return authResponseDTO;
    }
}
