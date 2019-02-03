package com.example.stayi.MachiningForces;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

//Хранилище переменных и параметров приложения.
public class Storage {
    private static final String STORAGE_NAME = "StorageName";

    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;
    @SuppressLint("StaticFieldLeak")
    private static Context context = null;

    public static void init(Context cntxt) {
        context = cntxt;
    }

    @SuppressLint("CommitPrefEdits")
    private static void init(){
        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }

    private static void settingsInit() {
        if (settings == null) init();
    }

    public static void addProperty(String name, boolean value) {
        settingsInit();
        editor.putBoolean( name, value );
        editor.apply();
    }

    public static void addProperty(String name, String value) {
        settingsInit();
        editor.putString(name, value);
        editor.apply();
    }

    public static boolean getProperty(String name, boolean b) {
        settingsInit();
        return settings.getBoolean( name, Boolean.parseBoolean(null));
    }

    public static String getProperty(String name) {
        settingsInit();
        return settings.getString(name, "0");
    }
}