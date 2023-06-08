package com.example.androcrypto;

import android.app.Application;
import android.content.Context;
// TODO: nom un peu moins générique
public class MyApplication extends Application {

    private static Context APPLICATION_CONTEXT;

    public static Context getContext() {
        return APPLICATION_CONTEXT;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        APPLICATION_CONTEXT = this.getApplicationContext();
    }
}
