package com.example.smartshop;

public class Owner {
    public String realname;
    public String surname;
    public String address;
    private static Owner user = null;

    private Owner(){
    }

    public static Owner getInstance() {
        if (user == null) {
            user = new Owner();
        }
        return user;

    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
