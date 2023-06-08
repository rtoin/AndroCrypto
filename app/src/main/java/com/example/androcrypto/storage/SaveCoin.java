package com.example.androcrypto.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.androcrypto.MyApplication;
// TODO: pas fan du nom, cette classe va servir à la gestion de toutes les sharedPreferences
public class SaveCoin {

    private static SaveCoin INSTANCE;

    private static final String SHARED_PREFERENCES_NAME = "saveCoin";
    // TODO: trouver un autre nom, on ne stock plus la clé API
    private static final String API_KEY = "apiKey";

    private final SharedPreferences preferences;

    private SaveCoin() {
        preferences = MyApplication.getContext()
                .getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static SaveCoin getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SaveCoin();
        }
        return INSTANCE;
    }

    public String getApiKey() {
        return preferences.getString(API_KEY, null);
    }

    public void setApiKey(String apiKey) {
        preferences.edit().putString(API_KEY, apiKey).apply();
    }
}
