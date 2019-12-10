package com.example.introslider;

import android.content.Context;
import android.content.SharedPreferences;

public class IntroPrefManager {
    public static final String SHOULD_SHOWN = "IS_SHOWN";
    public static final String INTRO_PREF = "Intro_pref";
    Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public IntroPrefManager(Context context) {
        this.context = context;
        sharedPreferences=context.getSharedPreferences(INTRO_PREF,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }
    public void setIntro(boolean set) {
        editor.putBoolean(SHOULD_SHOWN, set);
        editor.apply();
    }
    public boolean getState(){
        return sharedPreferences.getBoolean(SHOULD_SHOWN,false);
    }
}
