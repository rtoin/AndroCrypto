package com.example.mvvmexample.network;

import com.example.mvvmexample.models.PriceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

// https://square.github.io/retrofit/
public interface CoinRankingAPI {

    @Headers({
            "x-rapidapi-host: coinranking1.p.rapidapi.com",
            "x-rapidapi-key: efeaa5d79fmshe6fa6c8e321ac1dp1b25afjsnf399ab057be2"
    })
    @GET("/coin/Qwsogvtv82FCd/price")
    Call<PriceResponse> getBitcoinPrice();
}
