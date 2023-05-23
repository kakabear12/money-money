package com.example.moneymoney.service;

import com.example.moneymoney.entity.User;
import com.example.moneymoney.entity.VerificationToken;
import com.example.moneymoney.model.requestmodel.LoginModel;
import com.example.moneymoney.model.requestmodel.LogoutModel;
import com.example.moneymoney.model.requestmodel.RefreshTokenModel;
import com.example.moneymoney.model.requestmodel.SignUpModel;
import com.example.moneymoney.utils.ResponseObject;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {
    User registerUser(SignUpModel userModel);

    void saveVerificationTokenForUser(String token, User user);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    boolean checkIfValidOldPassword(User user, String oldPassword);


    ResponseEntity<ResponseObject> validateLoginForm(LoginModel loginModel);
    ResponseEntity<ResponseObject> login(LoginModel loginModel);

    ResponseEntity<ResponseObject> validateAccessToken();

    ResponseEntity<ResponseObject> refreshAccessToken(HttpServletRequest request, RefreshTokenModel refreshTokenModel);
    ResponseEntity<ResponseObject> logout(HttpServletRequest request, LogoutModel logoutModel);
}
