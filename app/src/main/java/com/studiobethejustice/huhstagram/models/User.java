package com.studiobethejustice.huhstagram.models;

public class User {

    private String user_id;
    private String phone_number;
    private String username;
    private String email;

    public User(){

    }

    public User(String user_id, String phone_number, String username, String email) {
        this.user_id = user_id;
        this.phone_number = phone_number;
        this.username = username;
        this.email = email;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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
}