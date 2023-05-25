package com.example.androcrypto.models;

import java.util.List;

public class CoinsResponseData {

    List<CoinsResponseCoin> coins;

    public List<CoinsResponseCoin> getCoins() {
        return coins;
    }

    public void setCoins(List<CoinsResponseCoin> coins) {
        this.coins = coins;
    }
}
