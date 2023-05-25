package com.example.androcrypto.viewmodels;

import androidx.lifecycle.LiveData;

import com.example.androcrypto.models.SampleModel;

public interface IViewModel {
    LiveData<SampleModel> getData();
    void generateNextValue();
}
