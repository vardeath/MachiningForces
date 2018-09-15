package com.example.stayi.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

//Хранилище переменных, которые используются в навигации приложения.
class localsettings {
    private static final String STORAGE_NAME = "StorageName";

    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;
    @SuppressLint("StaticFieldLeak")
    private static Context context = null;

    static void init(Context cntxt){
        context = cntxt;
    }

    private static void init(){
        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }

    static void addProperty(String name, boolean value){
        if( settings == null ){
            init();
        }
        editor.putBoolean( name, value );
        editor.commit();
    }

    static boolean getProperty(String name, boolean b){
        if( settings == null ){
            init();
        }
        return settings.getBoolean( name, Boolean.parseBoolean(null));
    }
}
