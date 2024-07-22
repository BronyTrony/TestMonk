package com.webserver.demo.models;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Setter
@Getter
public class User {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    private final Date date= new Date();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "\"login\":\"" + login + '\"' +
                "\"password\":\"" + password + '\"' +
                "\"date\":\"" + date + '\"' +
                '}';
    }
}
