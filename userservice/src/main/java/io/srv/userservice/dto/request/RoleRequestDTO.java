package io.srv.userservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequestDTO {

    @NotEmpty(message = "Please enter role name")
    private String name;
}
