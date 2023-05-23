package com.example.moneymoney.utils;

import com.example.moneymoney.enums.Role;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
public class PrincipalDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;


    private Boolean status;
    private Role role;

    public Role getRole() {
        return role;
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
}
