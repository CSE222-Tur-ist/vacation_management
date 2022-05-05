package com;


public class User extends Utilities implements IUser{

    private String username;



    private String name;
    private String surName;
    private final String ID;
    private String password;

    private String role;
    private String email;

    public User(String username,String name, String surName, String ID, String password,String role,String email) {
        this.username = username;
        this.name = name;
        this.surName = surName;
        this.ID = ID;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurName() {
        return surName;
    }

    @Override

    public String getID() { return ID; }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getRole() { return role; }

    @Override
    public String getEmail() { return email; }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setSurName(String surName) {
        this.surName = surName;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

}

