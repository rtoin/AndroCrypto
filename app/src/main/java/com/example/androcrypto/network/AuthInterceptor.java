package com.example.androcrypto.network;

import androidx.annotation.NonNull;
// TODO: inutile
import com.example.androcrypto.storage.SaveCoin;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {

    private static final String HOST_HEADER_NAME = "x-rapidapi-host";
    private static final String HOST_HEADER_VALUE = "coinranking1.p.rapidapi.com";
    private static final String KEY_HEADER_NAME = "x-rapidapi-key";

    private static final String KEY_HEADER_VALUE = "843d18442emsh7a9f5376a584e16p15746djsn1daba9193156";
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        // TODO: pas besoin, pourquoi ne pas utiliser directement la constante ?
        String apiKey = KEY_HEADER_VALUE;

        Request request = new Request.Builder(chain.request())
                .addHeader(HOST_HEADER_NAME, HOST_HEADER_VALUE)
                .addHeader(KEY_HEADER_NAME, apiKey)
                .build();

        return chain.proceed(request);
    }
}
