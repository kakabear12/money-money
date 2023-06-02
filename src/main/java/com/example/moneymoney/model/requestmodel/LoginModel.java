package com.example.moneymoney.model.requestmodel;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginModel {
    @Schema(description = "input email",example = "quangvanpham02022001@gmail.com")
    private String email;

    @Schema(description = "input user password",example = "123456")
    @Size(min = 6, max = 60)
    private String password;
}
