package com.example.androcrypto.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androcrypto.models.CoinsResponse;
import com.example.androcrypto.models.ListModel;
import com.example.androcrypto.models.PriceResponse;
import com.example.androcrypto.models.SampleModel;
import com.example.androcrypto.network.RetrofitNetworkManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetrofitViewModel extends ViewModel implements IViewModel {

    private final MutableLiveData<SampleModel> data = new MutableLiveData<>();

    private final MutableLiveData<ListModel> dataList = new MutableLiveData<>();

    public LiveData<SampleModel> getData() {
        return data;
    }

    @Override
    public void generateNextValue() {
        RetrofitNetworkManager.coinRankingAPI.getBitcoinPrice().enqueue(new Callback<PriceResponse>() {
            @Override
            public void onResponse(Call<PriceResponse> call, Response<PriceResponse> response) {
                if (response.body() != null) {
                    handleResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<PriceResponse> call, Throwable t) {
                // NO-OP
            }
        });
    }

    private void handleResponse(PriceResponse response) {
        data.postValue(new SampleModel(response.getData().getPrice()));
    }

    public void generateCoinList() {
        RetrofitNetworkManager.coinRankingAPI.getCoinList().enqueue(new Callback<CoinsResponse>() {
            @Override
            public void onResponse(Call<CoinsResponse> call, Response<CoinsResponse> response) {
                if (response.body() != null) {
                    handleCoinListResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<CoinsResponse> call, Throwable t) {
                // NO-OP
            }
        });
    }

    private void handleCoinListResponse(CoinsResponse response) {
        dataList.postValue(new ListModel(response.getData().getCoins()));
    }
}