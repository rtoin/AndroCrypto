package com.example.androcrypto.viewmodels;

import androidx.lifecycle.LiveData;

import com.example.androcrypto.models.Coin;

import java.util.List;

public interface IViewModel {
    LiveData<List<Coin>> getDataCoinList();
    void generateCoinList();
}
