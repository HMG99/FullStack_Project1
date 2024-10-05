package com.digi.model;

import com.digi.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String surname;
    private int year;
    private String email;
    private String password;
    private Status status;
    private String verifyCode;
    private String resetToken;
}
