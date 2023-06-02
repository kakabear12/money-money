package com.example.moneymoney.controller;

import com.example.moneymoney.entity.User;
import com.example.moneymoney.entity.VerificationToken;
import com.example.moneymoney.event.RegistrationCompleteEvent;
import com.example.moneymoney.model.requestmodel.*;
import com.example.moneymoney.service.UserService;
import com.example.moneymoney.utils.ResponseObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/money-money/accounts")
@Slf4j
public class AccountController {
    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private JavaMailSender mailSender;

    private User theUser;

    @PostMapping()
    @Operation(summary = " For registering account", description = "test")
    public String registerUser(@RequestBody SignUpModel userModel, final HttpServletRequest request){
        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(
                user,
                applicationUrl(request)
        ));
        return "Success!  Please check your email for to complete your registration";

    }
    @PostMapping("/password-reset")
    @Operation(summary = "For resetting password", description = "test")
    public String resetPassword(@RequestBody ResetPasswordModel passwordModel, HttpServletRequest request){
        theUser = userService.findUserByEmail(passwordModel.getEmail());
        String url = "";
        if(theUser != null){
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(theUser, token);
            url = passwordResetTokenMail(theUser, applicationUrl(request), token);
        }

        return url;
    }


    @GetMapping("/verification")
    @Operation(summary = "For sending verification token after register account")
    public String verifyRegistration(@RequestParam("token") String token){
        String result = userService.validateVerificationToken(token);
        if (result.equalsIgnoreCase("valid")){
            return "User Verifies Successfully";
        }
        return "Bad User";
    }

    @GetMapping("/verification/resend")
    @Operation(summary = "For resending verification token after register account")
    public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request){

        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        resendVerificationTokenMail(user, applicationUrl(request),verificationToken);
        return "Verification Link Sent";
    }



    @Operation(summary = "For login")
    @PostMapping(value="/authentication", produces = MediaType.APPLICATION_JSON_VALUE)
    @SecurityRequirements
    public ResponseEntity<ResponseObject> login(@Valid @RequestBody LoginModel loginForm) {
        if(loginForm.getEmail().isEmpty() || loginForm.getEmail().isBlank() || loginForm.getEmail() == null){
            return new ResponseEntity<ResponseObject>(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Please input username", null, null), HttpStatus.BAD_REQUEST);
        }
        if(loginForm.getPassword().isEmpty() || loginForm.getPassword().isBlank() || loginForm.getPassword() == null){
            return new ResponseEntity<ResponseObject>(new ResponseObject(HttpStatus.BAD_REQUEST.toString(), "Please input password", null, null), HttpStatus.BAD_REQUEST);
        }
        return userService.login(loginForm);
    }

    @PostMapping("/password-save")
    @Operation(summary = "For saving new password after confirm email")
    public String savePassword(@RequestParam("token") String token, @RequestBody SaveResetPasswordModel passwordModel){
        String result = userService.validatePasswordResetToken(token);
        if (!result.equalsIgnoreCase("valid")){
            return "Invalid Token";
        }
        Optional<User> user = userService.getUserByPasswordResetToken(token);
        if(user.isPresent()){
            userService.changePassword(user.get(), passwordModel.getNewPassword());
            return "Password Reset Successfully";
        } else{
            return "Invalid Token";
        }
    }


    private String passwordResetTokenMail(User user, String applicationUrl, String token) {
        String url = applicationUrl
                + "/password-reset?token="
                + token;

        //sendResetTokenMail()
        try {
            sendResetTokenMail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        log.info("Click the link to Reset your Password: {}",
                url);

        return url;
    }
    public void sendResetTokenMail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email For Reset password";
        String senderName = "User Registration Portal Service";
        String mailContent = "<p> Hi, "+ theUser.getFirstName()+ ", </p>"+
                "<p>Thank you for using this servive with us,"+"" +
                "Please, follow the link below to complete your reset Password.</p>"+
                "<a href=\"" +url+ "\">Click here to reset your password</a>"+
                "<p> Thank you <br> Users Registration Portal Service";
        MimeMessage message = mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("qvanwork@outlook.com.vn", senderName);
        messageHelper.setTo(theUser.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);
        mailSender.send(message);
    }
    private String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                "/api/v1/money-money/accounts" +
                request.getContextPath();
    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
        String url = applicationUrl
                + "/verification/resend?token="
                + verificationToken.getToken();

        //sendVerificationEmail()
        log.info("Click the link to verify your account: {}",
                url);
    }

}
