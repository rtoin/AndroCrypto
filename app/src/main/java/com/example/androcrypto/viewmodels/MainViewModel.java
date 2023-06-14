package com.example.androcrypto.viewmodels;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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

    public MainViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
        dataCoinList = dataRepository.getData();
    }

    @Override
    public LiveData<List<Coin>> getDataCoinList() {
        return dataCoinList;
    }

    public void generateCoinList() {
        RetrofitNetworkManager.coinRankingAPI.getCoinList().enqueue(new Callback<CoinListResponse>() {
            @Override
            public void onResponse(Call<CoinListResponse> call, Response<CoinListResponse> response) {
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
        Toast toast = Toast.makeText(this.getApplication(), "You are disconnected!", Toast.LENGTH_SHORT);
        toast.show();
    }
}