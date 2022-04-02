package io.srv.userservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDTO {

    @NotEmpty(message = "Please enter username")
    private String userName;

    @NotEmpty(message = "Please enter password")
    private String password;
}
