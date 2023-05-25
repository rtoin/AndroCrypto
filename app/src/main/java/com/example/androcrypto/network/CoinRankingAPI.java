package com.example.androcrypto.network;

import com.example.androcrypto.models.CoinsResponse;
import com.example.androcrypto.models.PriceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

// https://square.github.io/retrofit/
public interface CoinRankingAPI {

    @Headers({
            "x-rapidapi-host: coinranking1.p.rapidapi.com",
            "x-rapidapi-key: 843d18442emsh7a9f5376a584e16p15746djsn1daba9193156"
    })
    @GET("/coin/Qwsogvtv82FCd/price")
    Call<PriceResponse> getBitcoinPrice();

    @Headers({
            "x-rapidapi-host: coinranking1.p.rapidapi.com",
            "x-rapidapi-key: 843d18442emsh7a9f5376a584e16p15746djsn1daba9193156"
    })
    @GET("/coins?referenceCurrencyUuid=yhjMzLPhuIDl&timePeriod=24h&tiers%5B0%5D=1&orderBy=marketCap&orderDirection=desc&limit=50&offset=0")
    Call<CoinsResponse> getCoinList();
}
