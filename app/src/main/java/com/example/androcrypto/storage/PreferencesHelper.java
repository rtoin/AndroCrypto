package com.example.androcrypto.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.androcrypto.AndroCryptoApplication;

public class PreferencesHelper {

    private static PreferencesHelper INSTANCE;

    private static final String SHARED_PREFERENCES_NAME = "androCryptoPreferences";
    private static final String FAVORITE_COIN_KEY = "favoriteCoin";

    private final SharedPreferences preferences;

    private PreferencesHelper() {
        preferences = AndroCryptoApplication.getContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static PreferencesHelper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PreferencesHelper();
        }
        return INSTANCE;
    }

    public String getFavoriteCoin() {
        return preferences.getString(FAVORITE_COIN_KEY, null);
    }

    public void setFavoriteCoin(String coinName) {
        preferences.edit().putString(FAVORITE_COIN_KEY, coinName).apply();
    }
}
