package com;

public class User implements IUser {

    private String name;
    private String surName;
    private final String ID;
    private String password;

    public User(String name, String surName, String ID, String password) {
        this.name = name;
        this.surName = surName;
        this.ID = ID;
        this.password = password;
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
    public String getID() {
        return ID;
    }

    @Override
    public String getPassword() {
        return password;
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

}
