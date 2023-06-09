package com.example.androcrypto.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.androcrypto.AndroCryptoApplication;

public class PreferencesHelper {

    private static PreferencesHelper INSTANCE;

    private static final String SHARED_PREFERENCES_NAME = "androCryptoPreferences";
    private static final String FAVORITE_COIN_NAME = "favoriteCoin";
    private static final String FAVORITE_COIN_UUID = "favoriteCoinUuid";

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
        return preferences.getString(FAVORITE_COIN_NAME, null);
    }

    public void setFavoriteCoin(String coinName) {
        preferences.edit().putString(FAVORITE_COIN_NAME, coinName).apply();
    }

    public String getFavoriteCoinUuid() {
        return preferences.getString(FAVORITE_COIN_UUID, null);
    }

    public void setFavoriteCoinUuid(String coinUuid) {
        preferences.edit().putString(FAVORITE_COIN_UUID, coinUuid).apply();
    }
}
