package com.example.androcrypto.viewmodels;

import androidx.lifecycle.LiveData;

import com.example.androcrypto.models.Coin;
import com.example.androcrypto.models.SampleModel;

import java.util.List;

public interface IViewModel {
    LiveData<SampleModel> getData();
    void generateNextValue();

    LiveData<List<Coin>> getDataCoins();
    void generateCoinList();
}
