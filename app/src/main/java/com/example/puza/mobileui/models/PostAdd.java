package com.example.puza.mobileui.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

public class PostAdd {

    private String status;
    private String message;

    public PostAdd(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
