package com.example.aplikasiowner.Adapter;

import java.io.Serializable;

public class Request_Register implements Serializable {
    private String username, email, password, notelp;

    public Request_Register() {
    }

    public Request_Register(String username, String email, String password, String notelp) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.notelp = notelp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    @Override
    public String toString() {
        return "Register{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", notelp='" + notelp + '\'' +
                '}';
    }
}
