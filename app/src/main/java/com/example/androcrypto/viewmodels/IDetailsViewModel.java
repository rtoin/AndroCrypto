package com.example.androcrypto.viewmodels;

import androidx.lifecycle.LiveData;

import com.example.androcrypto.models.Coin;

public interface IDetailsViewModel {

    LiveData<Coin> getDataCoin();

    void generateCoin(String uuid);
}
