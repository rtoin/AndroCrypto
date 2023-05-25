package com.example.androcrypto.models;

public class CoinsResponse {

    private String status;

    private CoinsResponseData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CoinsResponseData getData() {
        return data;
    }

    public void setData(CoinsResponseData data) {
        this.data = data;
    }
}
