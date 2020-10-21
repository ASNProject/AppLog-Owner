package com.example.aplikasiowner.Adapter;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class RequestDatabase {
    public String username, alamat;

    public RequestDatabase() {
    }

    public RequestDatabase(String username, String alamat) {
        this.username = username;
        this.alamat = alamat;
    }
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("username", username);
        result.put("alamat", alamat);
        return result;
    }

}
