package com.example.androcrypto.viewmodels;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androcrypto.models.Coin;
import com.example.androcrypto.models.CoinResponse;
import com.example.androcrypto.network.RetrofitNetworkManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsViewModel extends AndroidViewModel implements IDetailsViewModel {

    private final MutableLiveData<Coin> dataCoin = new MutableLiveData<>();

    public DetailsViewModel(@NonNull Application application) {
        super(application);
    }

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
                handleCoinError();
            }
        });
    }

    private void handleCoinResponse(CoinResponse response){
        dataCoin.postValue(response.getData().getCoin());
    }

    private void handleCoinError() {
        Toast toast = Toast.makeText(this.getApplication(), "You are disconnected!", Toast.LENGTH_LONG);
        toast.show();
    }
}
