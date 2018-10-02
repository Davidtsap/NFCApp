package com.app.sogal.Data;

import android.media.Image;

import java.util.List;

public class User {
    String _id;
    String name;
    String email;
    String phone;
    String token;
    List<Chip> userListOfChips;
    //Image userImage;

    public String get_id() {
        return _id;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return this.token;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Chip> getUserListOfChips() {
        return userListOfChips;
    }

    public void setUserListOfChips(List<Chip> userListOfChips) {
        this.userListOfChips = userListOfChips;
    }


//    public Image getUserImage() {
//        return userImage;
//    }
//
//    public void setUserImage(Image userImage) {
//        this.userImage = userImage;
//    }
}
