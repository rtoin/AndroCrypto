package com.example.androcrypto.models;

public class CoinListResponse {

    private String status;

    private CoinListData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CoinListData getData() {
        return data;
    }

    public void setData(CoinListData data) {
        this.data = data;
    }
}