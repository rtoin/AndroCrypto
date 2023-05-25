package com.example.mvvmexample.network;

import com.example.mvvmexample.NetworkConstants;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpNetworkManager {

    public static final OkHttpNetworkManager INSTANCE;

    static {
        INSTANCE = new OkHttpNetworkManager();
    }

    private final OkHttpClient client = new OkHttpClient();

    private OkHttpNetworkManager() {}

    public Call bitcoinPriceRequest() {
        Request request = baseRequestBuilder()
                .url(NetworkConstants.BASE_URL + NetworkConstants.BITCOIN_PRICE_PATH)
                .get()
                .build();

        return client.newCall(request);
    }

    private Request.Builder baseRequestBuilder() {
        return new Request.Builder()
                .addHeader(NetworkConstants.HOST_HEADER_NAME, NetworkConstants.HOST_HEADER_VALUE)
                .addHeader(NetworkConstants.KEY_HEADER_NAME, NetworkConstants.KEY_HEADER_VALUE);
    }
}
