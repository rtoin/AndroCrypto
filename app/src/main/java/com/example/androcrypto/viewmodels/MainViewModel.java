package com.example.androcrypto.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androcrypto.models.Coin;
import com.example.androcrypto.models.SampleModel;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;
// TODO: à supprimer
public class MainViewModel extends ViewModel implements IViewModel {

    private final MutableLiveData<SampleModel> data = new MutableLiveData<>();
    private final MutableLiveData<List<Coin>> dataCoins = new MutableLiveData<>();

    public LiveData<SampleModel> getData() {
        return data;
    }

    public void generateNextValue() {
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        SampleModel newData = new SampleModel(generatedString);
        data.postValue(newData);
    }

    public LiveData<List<Coin>> getDataCoins() {
        return dataCoins;
    }

    @Override
    public void generateCoinList() {

    }
}
