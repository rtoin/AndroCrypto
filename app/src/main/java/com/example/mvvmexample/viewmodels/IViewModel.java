package com.example.mvvmexample.viewmodels;

import androidx.lifecycle.LiveData;

import com.example.mvvmexample.models.SampleModel;

public interface IViewModel {
    LiveData<SampleModel> getData();
    void generateNextValue();
}
