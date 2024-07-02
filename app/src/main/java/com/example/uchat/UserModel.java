package com.example.uchat;

public class UserModel {
    String Username;
    String PhoneNumber;
    int img;

    public UserModel() {
    }

    public UserModel(String Username, String PhoneNumber, int img) {
        this.Username = Username;
        this.PhoneNumber = PhoneNumber;
        this.img = img;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String tvUsername) {
        this.Username = tvUsername;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setTvPhoneNumber(String tvPhoneNumber) {
        this.PhoneNumber = tvPhoneNumber;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
