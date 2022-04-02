package io.srv.userservice.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddRoleToUserDTO {

    @NotEmpty(message = "Please provide username")
    private String userName;

    @NotEmpty(message = "Please provide rolename")
    private String roleName;
}
