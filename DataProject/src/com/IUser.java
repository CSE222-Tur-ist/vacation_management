package com;

public interface IUser {

    public String getUsername();

    public String getName();

    public String getSurName();

    public String getID();

    public String getPassword();


    public User.userType getRole();

    public String getEmail();

    void setUsername(String username);

    void setName(String name);

    void setSurName(String surName);

    void setPassword(String password);

    void setRole(User.userType role);

    void setEmail(String email);

}
