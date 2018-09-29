package com.app.sogal.Data;

import android.media.Image;

import java.util.List;

public class User {
    String userName;
    String userEmail;
    String userPhone;
    //List<Chip> userListOfChips;
    Image userImage;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

//    public List<Chip> getUserListOfChips() {
//        return userListOfChips;
//    }
//
//    public void setUserListOfChips(List<Chip> userListOfChips) {
//        this.userListOfChips = userListOfChips;
//    }

    public Image getUserImage() {
        return userImage;
    }

    public void setUserImage(Image userImage) {
        this.userImage = userImage;
    }
}
