package io.srv.userservice.dto.response;

import lombok.Data;

@Data
public class AuthResponseDTO {

    private String accessToken;
    private String refreshToken;

}
