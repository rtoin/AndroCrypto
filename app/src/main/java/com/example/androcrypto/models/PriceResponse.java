package com.example.androcrypto.models;

public class PriceResponse {

    private String status;
    private PriceResponseData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PriceResponseData getData() {
        return data;
    }

    public void setData(PriceResponseData data) {
        this.data = data;
    }
}
