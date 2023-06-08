package com.example.androcrypto.network;

import com.example.androcrypto.models.CoinResponse;
import com.example.androcrypto.models.CoinsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

// https://square.github.io/retrofit/
public interface CoinRankingAPI {

    @GET("/coins?referenceCurrencyUuid=yhjMzLPhuIDl&timePeriod=24h&tiers%5B0%5D=1&orderBy=marketCap&orderDirection=desc&limit=50&offset=0")
    Call<CoinsResponse> getCoinList();

    @GET("/coin/{uuid}?referenceCurrency=yhjMzLPhuIDl&timePeriod=24h")
    Call<CoinResponse> getCoin(@Path("uuid") String uuid);
}
