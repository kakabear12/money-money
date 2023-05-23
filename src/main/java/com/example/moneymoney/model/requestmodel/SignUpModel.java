package com.example.moneymoney.model.requestmodel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpModel {
    @Schema(description = "First Name of user", example = "Van",required = true)
    @Size(max = 20)
    @NotBlank(message = "firstName not null")
    private String firstName;


    @Schema(description = "Last Name of user", example = "Nguyen",required = true)
    @Size(max = 20)
    @NotBlank(message = "lastName not null")
    private String lastName;

    @Email
    @Schema(description = "Email of user", example = "vanng@gmail.com",required = true)
    @NotBlank(message = "Email not null")
    private String email;

    @Schema(description = "password", required = true)
    @Size(min = 6,max = 60)
    @NotBlank(message = "password not null")
    private String password;
    //private String matchingPassword;

}
