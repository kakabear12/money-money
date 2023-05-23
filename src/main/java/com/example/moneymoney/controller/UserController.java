package com.example.moneymoney.controller;

import com.example.moneymoney.entity.User;
import com.example.moneymoney.model.requestmodel.LogoutModel;
import com.example.moneymoney.model.requestmodel.PasswordModel;
import com.example.moneymoney.model.requestmodel.RefreshTokenModel;
import com.example.moneymoney.model.requestmodel.SaveResetPasswordModel;
import com.example.moneymoney.service.UserService;
import com.example.moneymoney.utils.ResponseObject;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("api/v1/money-money/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/password-change")
    @Operation(summary = "For changing password ")
    public String changPassword(@RequestBody PasswordModel passwordModel){
        User user = userService.findUserByEmail(passwordModel.getEmail());
        if (!userService.checkIfValidOldPassword(user, passwordModel.getOldPassword())){
            return "Invalid Password";
        }

        // Save New Password;
        userService.changePassword(user, passwordModel.getNewPassword());
        return "Password Changed Successfully ";
    }



    @PostMapping(value = "/validation", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "For getting user information after login")
    public ResponseEntity<ResponseObject> reloadUserByJWT() {
        return userService.validateAccessToken();
    }

    @PostMapping(value = "/accessToken", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "For getting new access token by refresh token after it expired")
    public ResponseEntity<ResponseObject> refreshAccessToken(HttpServletRequest request, @Valid @RequestBody RefreshTokenModel refreshTokenForm) {
        if(refreshTokenForm.getRefreshToken() == null || refreshTokenForm.getRefreshToken().isEmpty() || refreshTokenForm.getRefreshToken().isBlank()){
            return new ResponseEntity<ResponseObject>(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Please input refresh token", null, null), HttpStatus.BAD_REQUEST);
        }
        return userService.refreshAccessToken(request, refreshTokenForm);
    }

    @PostMapping(value = "/sessions", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "For logout")
    public ResponseEntity<ResponseObject> logout(HttpServletRequest request, @Valid @RequestBody LogoutModel logoutForm) {
        if(logoutForm.getRefreshToken() == null || logoutForm.getRefreshToken().isEmpty() || logoutForm.getRefreshToken().isBlank()){
            return new ResponseEntity<ResponseObject>(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Please input refresh token", null, null), HttpStatus.BAD_REQUEST);
        }
        return userService.logout(request, logoutForm);
    }
}
