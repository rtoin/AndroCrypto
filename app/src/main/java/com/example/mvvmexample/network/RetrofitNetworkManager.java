package com.example.mvvmexample.network;

import com.example.mvvmexample.NetworkConstants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetworkManager {

    public static final CoinRankingAPI coinRankingAPI;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        coinRankingAPI = retrofit.create(CoinRankingAPI.class);
    }
}
