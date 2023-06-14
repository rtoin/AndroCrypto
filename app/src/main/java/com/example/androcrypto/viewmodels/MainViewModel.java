package com.example.androcrypto.viewmodels;

import android.app.Application;
import android.widget.Toast;

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

    private final MutableLiveData<Coin> dataCoin = new MutableLiveData<>();

    private final DataRepository dataRepository;
    private final LiveData<List<Coin>> data;

    public MainViewModel(@NonNull Application application) {
        super(application);
        dataRepository = new DataRepository(application);
        data = dataRepository.getData();
    }

    @Override
    public LiveData<List<Coin>> getDataCoinList() {
        return data;
    }

    public LiveData<Coin> getDataCoin() { return dataCoin; }

    public void generateCoinList() {
        RetrofitNetworkManager.coinRankingAPI.getCoinList().enqueue(new Callback<CoinListResponse>() {
            @Override
            public void onResponse(Call<CoinListResponse> call, Response<CoinListResponse> response) {
                if (response.body() != null) {
                    handleCoinListResponse(response.body());
                } else {
                    handleCoinListError();
                }
            }

            @Override
            public void onFailure(Call<CoinListResponse> call, Throwable t) {
                t.printStackTrace();
                handleCoinListError();
            }
        });
    }

    private void handleCoinListResponse(CoinListResponse response) {
        for(Coin coin : response.getData().getCoins()) {
            dataRepository.insertData(coin);
        }
    }

    private void handleCoinListError() {
        CharSequence message = "You are disconnected!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this.getApplication(), message, duration);
        toast.show();
    }
}