package com.example.stayi.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

//Хранилище переменных использующихся в навигации приложения.
public class nav_var_storage {
    private static final String STORAGE_NAME = "StorageName";

    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;
    @SuppressLint("StaticFieldLeak")
    private static Context context = null;

    static void init(Context cntxt) {
        context = cntxt;
    }

    @SuppressLint("CommitPrefEdits")
    private static void init(){
        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }

    static void addProperty(String name, boolean value) {
        if( settings == null ){
            init();
        }
        editor.putBoolean( name, value );
        editor.apply();
    }

    static void addProperty(String name, String value) {
        if (settings == null) {
            init();
        }
        editor.putString(name, value);
        editor.apply();
    }

    static boolean getProperty(String name, boolean b) {
        if( settings == null ){
            init();
        }
        return settings.getBoolean( name, Boolean.parseBoolean(null));
    }

    static String getProperty(String name) {
        if (settings == null) {
            init();
        }
        return settings.getString(name, "0");
    }
}