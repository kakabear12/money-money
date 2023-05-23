package com.example.moneymoney.model.requestmodel;

import com.auth0.jwt.algorithms.Algorithm;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.commons.codec.digest.DigestUtils;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LogoutModel {

    @Pattern(regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")
    private String refreshToken;

    public String getRefreshToken() {
        return DigestUtils.sha3_256Hex(refreshToken);
    }

}
