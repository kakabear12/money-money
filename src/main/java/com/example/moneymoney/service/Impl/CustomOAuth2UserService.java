package com.example.moneymoney.service.Impl;

import com.example.moneymoney.entity.User;
import com.example.moneymoney.enums.AuthProvider;
import com.example.moneymoney.enums.Role;
import com.example.moneymoney.exception.OAuth2AuthenticationProcessingException;
import com.example.moneymoney.jwt.userprincipal.Principal;
import com.example.moneymoney.oauth2.user.OAuth2UserInfo;
import com.example.moneymoney.oauth2.user.OAuth2UserInfoFactory;
import com.example.moneymoney.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                updateProviderUser(user, oAuth2UserInfo, oAuth2UserRequest);
            }else {
                user = updateUser(user, oAuth2UserInfo);
            }
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return Principal.create(user, oAuth2User.getAttributes());
    }

    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        User user = User.builder()
                .provider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))
                .lastName(oAuth2UserInfo.getName())
                .firstName(oAuth2UserInfo.getName())
                .providerId(oAuth2UserInfo.getId())
                .email(oAuth2UserInfo.getEmail())
                .enable(true)
                .role(Role.USER)
                .build();
        return userRepository.save(user);
    }

    private User updateExistingUserWithGG(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setLastName(oAuth2UserInfo.getName());
        existingUser.setFirstName(oAuth2UserInfo.getName());
        return userRepository.save(existingUser);
    }
    private User updateExistingUserWithFB(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        int name = oAuth2UserInfo.getName().lastIndexOf(" ");
        String lastName = null;
        String firstName = null;
        if (name != -1) {
            lastName = oAuth2UserInfo.getName().substring(name + 1);
            firstName = oAuth2UserInfo.getName().substring(0, name);
        }else {
            lastName = "";
            firstName = "";
        }
        existingUser.setLastName(lastName);
        existingUser.setFirstName(firstName);
        return userRepository.save(existingUser);
    }
    private User updateUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        if (existingUser.getProvider().equals(AuthProvider.facebook)){
            updateExistingUserWithFB(existingUser, oAuth2UserInfo);
        }else if(existingUser.getProvider().equals(AuthProvider.google)) {
            updateExistingUserWithGG(existingUser, oAuth2UserInfo);
        }
        return userRepository.save(existingUser);
    }

    private User updateProviderUser(User existingUser, OAuth2UserInfo oAuth2UserInfo, OAuth2UserRequest oAuth2UserRequest) {
        existingUser.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        existingUser.setProviderId(oAuth2UserInfo.getId());
        updateUser(existingUser, oAuth2UserInfo);
        return userRepository.save(existingUser);
    }


}
