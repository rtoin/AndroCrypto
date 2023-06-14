package com.example.androcrypto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.androcrypto.databinding.ActivityMainBinding;
import com.example.androcrypto.models.Coin;
import com.example.androcrypto.viewmodels.IViewModel;

import com.example.androcrypto.viewmodels.RetrofitViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private IViewModel viewModel;

    private RecyclerViewAdapter adapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(RetrofitViewModel.class);

        binding.refreshButton.setOnClickListener(v -> {
            viewModel.generateCoinList();
        });

        adapter = new RecyclerViewAdapter(new ArrayList<>());
        adapter.setListener(new Listener() {
            @Override
            public void onClick(Coin coin) {
                intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("COIN_UUID", coin.getUuid());
                startActivity(intent);
            }
        });
        binding.coinList.setLayoutManager(new LinearLayoutManager(this));
        binding.coinList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getDataCoinList().observe(this, coins -> {
            adapter.setData(coins);
        });
    }
}