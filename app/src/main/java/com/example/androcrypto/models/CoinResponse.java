package com.example.androcrypto.models;

public class CoinResponse {

    private String status;

    private Coin data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Coin getData() {
        return data;
    }

    public void setData(Coin data) {
        this.data = data;
    }
}
