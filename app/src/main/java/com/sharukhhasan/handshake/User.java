package com.sharukhhasan.handshake;

import android.graphics.Bitmap;

import java.net.URL;

/**
 * Created by Sharukh on 3/21/16.
 */
public class User {
    public String userName;
    public String userEmail;
    public String userPhoneNumber;
    public String userCompany;
    public String userFacebookLink;
    public String userLinkedinName;
    public URL userPictureURL;
    public Bitmap userPicture;
    public String userFacebookId;

    public User() {}

    public User(String userName, String userEmail, String userPhoneNumber, String userCompany, String userFacebookLink, String userLinkedinName,
                URL userPictureURL, Bitmap userPicture, String userFacebookId){
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userCompany = userCompany;
        this.userFacebookLink = userFacebookLink;
        this.userLinkedinName = userLinkedinName;
        this.userPictureURL = userPictureURL;
        this.userPicture = userPicture;
        this.userFacebookId = userFacebookId;
    }

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

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserCompany() {
        return userCompany;
    }

    public void setUserCompany(String userCompany) {
        this.userCompany = userCompany;
    }

    public String getUserFacebookLink() {
        return userFacebookLink;
    }

    public void setUserFacebookLink(String userFacebookLink) {
        this.userFacebookLink = userFacebookLink;
    }

    public String getUserLinkedinName() {
        return userLinkedinName;
    }

    public void setUserLinkedinName(String userLinkedinName) {
        this.userLinkedinName = userLinkedinName;
    }

    public URL getUserPictureURL() {
        return userPictureURL;
    }

    public void setUserPictureURL(URL userPictureURL) {
        this.userPictureURL = userPictureURL;
    }

    public Bitmap getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(Bitmap userPicture) {
        this.userPicture = userPicture;
    }

    public String getUserFacebookId() {
        return userFacebookId;
    }

    public void setUserFacebookId(String userFacebookId) {
        this.userFacebookId = userFacebookId;
    }


}
