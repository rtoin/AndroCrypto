package com.example.androcrypto.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androcrypto.models.Coin;
import com.example.androcrypto.models.CoinResponse;
import com.example.androcrypto.network.RetrofitNetworkManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsViewModel extends ViewModel implements IDetailsViewModel {

    private final MutableLiveData<Coin> dataCoin = new MutableLiveData<>();

    @Override
    public LiveData<Coin> getDataCoin() {
        return dataCoin;
    }

    @Override
    public void generateCoin(String uuid) {
        RetrofitNetworkManager.coinRankingAPI.getCoin(uuid).enqueue(new Callback<CoinResponse>() {

            @Override
            public void onResponse(Call<CoinResponse> call, Response<CoinResponse> response) {
                if(response.body() != null){
                    handleCoinResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<CoinResponse> call, Throwable t) {
                //TODO: gestion d'erreur
                t.printStackTrace();
            }
        });
    }

    private void handleCoinResponse(CoinResponse response){
        dataCoin.postValue(response.getData().getCoin());
    }
}
