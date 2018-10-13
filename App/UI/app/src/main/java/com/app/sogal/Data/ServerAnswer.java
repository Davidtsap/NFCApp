package com.app.sogal.Data;

public class ServerAnswer {
    int responseCode;
    String message;
    String token;

    public ServerAnswer(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public ServerAnswer() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
