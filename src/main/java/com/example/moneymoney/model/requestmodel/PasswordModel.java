package com.example.moneymoney.model.requestmodel;

import lombok.Data;

@Data
public class PasswordModel {
    private String email;
    private String oldPassword;
    private String newPassword;

}
