package com.example.androcrypto.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.androcrypto.AndroCryptoApplication;

public class Preferences {

    private static Preferences INSTANCE;

    private static final String SHARED_PREFERENCES_NAME = "androCryptoPreferences";
    private static final String FAVORITE_KEY = "favoriteKey";

    private final SharedPreferences preferences;

    private Preferences() {
        preferences = AndroCryptoApplication.getContext()
                .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static Preferences getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Preferences();
        }
        return INSTANCE;
    }

    public String getFavoriteKey() {
        return preferences.getString(FAVORITE_KEY, null);
    }

    public void setFavoriteKey(String apiKey) {
        preferences.edit().putString(FAVORITE_KEY, apiKey).apply();
    }
}
