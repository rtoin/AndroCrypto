package com.example.androcrypto.models;

import java.util.List;

public class ListModel {

    private List<CoinsResponseCoin> coins;

    public ListModel(List<CoinsResponseCoin> coins) {
        this.coins = coins;
    }

    public List<CoinsResponseCoin> getCoins() {
        return coins;
    }
}
