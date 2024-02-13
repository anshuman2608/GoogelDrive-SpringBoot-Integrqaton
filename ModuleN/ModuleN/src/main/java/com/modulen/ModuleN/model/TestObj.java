package com.modulen.ModuleN.model;


import org.springframework.boot.autoconfigure.domain.EntityScan;


public class TestObj {

    String Name;
    String Mobile;
    String Email;
    String Town;


    public TestObj(String name, String mobile, String email, String town) {
        Name = name;
        Mobile = mobile;
        Email = email;
        Town = town;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getTown() {
        return Town;
    }

    public void setTown(String town) {
        Town = town;
    }

    @Override
    public String toString() {
        return "TestObj{" +
                "Name='" + Name + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Email='" + Email + '\'' +
                ", Town='" + Town + '\'' +
                '}';
    }
}
