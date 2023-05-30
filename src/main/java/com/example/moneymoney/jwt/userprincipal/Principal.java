package com.example.moneymoney.jwt.userprincipal;

import com.example.moneymoney.entity.User;
import com.example.moneymoney.enums.Role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Principal implements UserDetails , OAuth2User {

    private Long id;

    private String firstName;
    private String lastName;
    @JsonIgnore
    private String password;

    private String email;


    private Boolean status;
    private Role role;
    @JsonIgnore
    private Collection<? extends GrantedAuthority> grantedAuthorities;
    private Map<String, Object> attributes;
    public static Principal build(User user) {
        List<GrantedAuthority> grantedAuthorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole().name()));
           return Principal.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .grantedAuthorities(grantedAuthorities)
                   .build();
    }
    public static Principal create(User user, Map<String, Object> attributes) {
        Principal userPrincipal = Principal.build(user);
        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }
    public Principal(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.grantedAuthorities = authorities;
    }

    public static Principal create(User user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("USER"));

        return new Principal(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }



    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }


    public String getEmail() {
        return email;
    }


    public Boolean getStatus() {
        return status;
    }

    public Role getRole() {
        return role;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }

}
