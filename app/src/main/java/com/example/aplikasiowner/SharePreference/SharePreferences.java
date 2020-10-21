package com.example.aplikasiowner.SharePreference;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferences {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;
    int private_mode = 0;
    private static  final String PREF_NAME="Owner";

    public SharePreferences (Context context){
        this.context = context;
        preferences = context.getSharedPreferences(PREF_NAME, private_mode);
        editor = preferences.edit();
    }
    public void setEmail (String email){
        editor.putString("email", email);
        editor.commit();
    }
    public String getEmail (){
        return preferences.getString("email", null);
    }

    public void setPassword (String password){
        editor.putString("password", password);
        editor.commit();
    }
    public String getPassword (){
        return preferences.getString("password", null);
    }
}
