package com.example.androcrypto.models;

public class Coin {

    private String name;

    private String symbol;

    private String price;

    private String description;

    private String uuid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getUuid() { return uuid; }

    public void setUuid(String uuid) { this.uuid = uuid; }
}
