package com.example.demo.utilities;

public class LoginDetails {
    private String userName;
    private String accessToken;
    private String url;

    public LoginDetails(String userName, String accessToken, String url) {
        this.userName = userName;
        this.accessToken = accessToken;
        this.url = url;
    }
    public LoginDetails() {

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
