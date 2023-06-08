package com.example.androcrypto.viewmodels;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.androcrypto.models.CoinResponse;
import com.example.androcrypto.models.CoinsResponse;
import com.example.androcrypto.models.Coin;
import com.example.androcrypto.network.RetrofitNetworkManager;
import com.example.androcrypto.storage.DataRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetrofitViewModel extends AndroidViewModel implements IViewModel {

    private final MutableLiveData<Coin> dataCoin = new MutableLiveData<>();

    private final DataRepository dataRepository;
    private final LiveData<List<Coin>> data;
    private Context MyApplication;

    public RetrofitViewModel(@NonNull Application application) {
        super(application);
        this.MyApplication = this.getApplication();
        dataRepository = new DataRepository(application);
        data = dataRepository.getData();
    }

    @Override
    public LiveData<List<Coin>> getDataCoins() {
        return data;
    }

    public LiveData<Coin> getDataCoin() { return dataCoin; }

    public void generateCoinList() {
        RetrofitNetworkManager.coinRankingAPI.getCoinList().enqueue(new Callback<CoinsResponse>() {
            @Override
            public void onResponse(Call<CoinsResponse> call, Response<CoinsResponse> response) {
                if (response.body() != null) {
                    handleCoinListResponse(response.body());
                } else {
                    handleCoinListError();
                }
            }

            @Override
            public void onFailure(Call<CoinsResponse> call, Throwable t) {
                t.printStackTrace();
                handleCoinListError();

                // TODO: gestion des erreurs
            }
        });
    }

    private void handleCoinListResponse(CoinsResponse response) {
        for(Coin coin : response.getData().getCoins()) {
            dataRepository.insertData(coin);
        }
    }

    private void handleCoinListError() {
        CharSequence text = "You are disconnected !";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this.MyApplication, text, duration);
        toast.show();
    }

    public void generateCoin(String uuid) {
        RetrofitNetworkManager.coinRankingAPI.getCoin(uuid).enqueue(new Callback<CoinResponse>() {
            @Override
            public void onResponse(Call<CoinResponse> call, Response<CoinResponse> response) {
                if (response.body() != null) {
                    handleCoinResponse(response.body());
                }
            }

            @Override
            public void onFailure(Call<CoinResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void handleCoinResponse(CoinResponse response) {
        dataCoin.postValue(response.getData());
    }
}