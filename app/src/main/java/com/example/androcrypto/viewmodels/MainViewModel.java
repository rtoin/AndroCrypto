package com.example.androcrypto.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androcrypto.models.Coin;
import com.example.androcrypto.models.CoinListResponse;
import com.example.androcrypto.network.RetrofitNetworkManager;
import com.example.androcrypto.storage.DataRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainViewModel extends AndroidViewModel implements IMainViewModel {

    private final DataRepository dataRepository;
    private final LiveData<List<Coin>> dataCoinList;

    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
        dataCoinList = dataRepository.getData();
    }

    @Override
    public LiveData<List<Coin>> getDataCoinList() {
        return dataCoinList;
    }

    @Override
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void generateCoinList() {
        RetrofitNetworkManager.coinRankingAPI.getCoinList().enqueue(new Callback<CoinListResponse>() {
            @Override
            public void onResponse(Call<CoinListResponse> call, Response<CoinListResponse> response) {
                // TODO: body null non géré ?
                if (response.body() != null) {
                    handleCoinListResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<CoinListResponse> call, Throwable t) {
                handleCoinListError();
            }
        });
    }

    private void handleCoinListResponse(CoinListResponse response) {
        for (Coin coin : response.getData().getCoins()) {
            dataRepository.insertData(coin);
        }
    }

    private void handleCoinListError() {
        errorMessage.postValue("You are disconnected!");
    }
}