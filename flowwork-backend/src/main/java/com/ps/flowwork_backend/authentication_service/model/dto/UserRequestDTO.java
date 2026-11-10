package com.ps.flowwork_backend.authentication_service.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    @NotEmpty(message = "First Name is Mandatory")
    private String firstName;
    private String lastName;
    @NotEmpty(message = "Email is Mandatory")
    @Email(message = "Email should be in valid format")
    private String email;
    @NotEmpty(message = "Password is Required")
    private String passwordHash;
}
