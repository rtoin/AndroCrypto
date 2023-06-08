package com.example.androcrypto.models;

import java.util.List;
// TODO: inutile
public class ListModel {

    private List<Coin> coins;

    public ListModel(List<Coin> coins) {
        this.coins = coins;
    }

    public List<Coin> getCoins() {
        return coins;
    }
}
