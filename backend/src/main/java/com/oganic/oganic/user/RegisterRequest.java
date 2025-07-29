package com.oganic.oganic.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank(message = "First name must not be blank")
    private String firstName;
    @NotBlank
    private String lastName;
    @Email(message = "Email is not valid")
    private String email;
    @Size(min = 8)
    private String password;
    @Size(min = 10, max = 12)
    private String phone;
    @NotNull
    private String address;
}
