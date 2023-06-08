package com.example.androcrypto;

public class NetworkConstants {
    // TODO: constantes inutilisées
    public static final String BASE_URL = "https://coinranking1.p.rapidapi.com";
    public static final String BITCOIN_PRICE_PATH = "/coin/Qwsogvtv82FCd/price";

    public static final String COIN_LIST_PATH = "/coins?referenceCurrencyUuid=yhjMzLPhuIDl&timePeriod=24h&tiers%5B0%5D=1&orderBy=marketCap&orderDirection=desc&limit=50&offset=0";

    public static final String HOST_HEADER_NAME = "x-rapidapi-host";
    public static final String HOST_HEADER_VALUE = "coinranking1.p.rapidapi.com";
    public static final String KEY_HEADER_NAME = "x-rapidapi-key";
    public static final String KEY_HEADER_VALUE = "843d18442emsh7a9f5376a584e16p15746djsn1daba9193156";
}
