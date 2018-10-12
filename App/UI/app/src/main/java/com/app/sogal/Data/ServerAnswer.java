package com.app.sogal.Data;

public class ServerAnswer {
    int responseCode;
    String message;

    public ServerAnswer(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    public ServerAnswer() {
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
