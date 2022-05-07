package com.entity;

public interface IUser {

    public String getUsername();

    void setUsername(String username);

    public String getName();

    void setName(String name);

    public String getSurName();

    void setSurName(String surName);

    public String getID();

    public String getPassword();

    void setPassword(String password);

    public User.userType getRole();

    void setRole(User.userType role);

    public String getEmail();

    void setEmail(String email);

}
