package com.example.puza.mobileui.models;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONObject;

public class Registernew {

    private Boolean status;
    private JSONArray message;

    public Registernew(Boolean status, JSONArray message) {
        this.status = status;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public JSONArray getMessage() {
        return message;
    }

    public void setMessage(JSONArray message) {
        this.message = message;
    }
}
