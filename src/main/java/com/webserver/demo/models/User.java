package com.webserver.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Setter
@Getter
public class User {
    @NotBlank
    public String login;
    @NotBlank
    public String password;
    public java.sql.Date date;
    public String email;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.email = login.toLowerCase() + "@email.com";
        this.date= new Date(System.currentTimeMillis());
    }

    public User(String login, String password, Date date, String email) {
        this.login = login;
        this.password = password;
        this.date = date;
        this.email = email;
    }

    @Override
    public String toString() {
        return "{" +
                "\"login\":\"" + login + "\"," +
                "\"password\":\"" + password + "\"," +
                "\"date\":\"" + date + "\"," +
                "\"email\":\"" + email + "\"" +
                '}';
    }
}
