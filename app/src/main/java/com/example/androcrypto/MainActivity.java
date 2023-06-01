package com.example.androcrypto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.androcrypto.databinding.ActivityMainBinding;
import com.example.androcrypto.models.Coin;
import com.example.androcrypto.storage.SaveCoin;
import com.example.androcrypto.viewmodels.IViewModel;
import com.example.androcrypto.viewmodels.MainViewModel;
import com.example.androcrypto.viewmodels.RetrofitViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private IViewModel viewModel;

    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);

        binding.nextValueButton.setOnClickListener(v -> {
            viewModel.generateNextValue();
            viewModel.generateCoinList();
        });

        adapter = new RecyclerViewAdapter(new ArrayList<>());
        adapter.setListener(new Listener() {
            @Override
            public void onClick(Coin coin) {
                SaveCoin.getInstance().setApiKey(coin.getName());
                System.out.println(SaveCoin.getInstance().getApiKey());
            }
        });
        binding.coinList.setLayoutManager(new LinearLayoutManager(this));
        binding.coinList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getDataCoins().observe(this, coins -> {
            adapter.setData(coins);
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.getData().removeObservers(this);
    }
}