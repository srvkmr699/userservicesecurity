package io.srv.userservice.dto.request;

import lombok.Data;

@Data
public class AddRoleToUserDTO {
    private String userName;
    private String roleName;
}
