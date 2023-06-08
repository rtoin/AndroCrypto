package com.example.androcrypto.models;

public class CoinsResponse {

    private String status;

    private CoinsData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CoinsData getData() {
        return data;
    }

    public void setData(CoinsData data) {
        this.data = data;
    }
}