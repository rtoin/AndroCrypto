package com.example.androcrypto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.androcrypto.databinding.ActivityMainBinding;
import com.example.androcrypto.models.Coin;
import com.example.androcrypto.storage.PreferencesHelper;
import com.example.androcrypto.viewmodels.IMainViewModel;
import com.example.androcrypto.viewmodels.MainViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private IMainViewModel viewModel;

    private RecyclerViewAdapter adapter;
    private Intent intent;

    private Intent notificationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notificationIntent = new Intent(this, NotificationService.class);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        //Refresh button
        binding.refreshButton.setOnClickListener(v -> {
            viewModel.generateCoinList();
        });

        //Coin list recycler view
        adapter = new RecyclerViewAdapter(new ArrayList<>());
        adapter.setListener(new CoinListener() {
            @Override
            public void onClick(Coin coin) {
                intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("COIN_UUID", coin.getUuid());
                startActivity(intent);
            }
        });
        binding.coinList.setLayoutManager(new LinearLayoutManager(this));
        binding.coinList.setAdapter(adapter);

        //Error toast
        viewModel.getErrorMessage().observe(this, message -> {
            Toast toast = Toast.makeText(this.getApplication(), message, Toast.LENGTH_SHORT);
            toast.show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(notificationIntent);

        viewModel.getDataCoinList().observe(this, coins -> {
            adapter.setData(coins);
        });
    }
}