package com.example.recylerapp;

public class Users {
    String Name,Number,desc,location,item;

    public Users() {
    }

    public Users(String name, String number,String desc, String location,String item) {
        Name = name;
        Number = number;
        this.desc = desc;
        this.location = location;
        this.item = item;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
