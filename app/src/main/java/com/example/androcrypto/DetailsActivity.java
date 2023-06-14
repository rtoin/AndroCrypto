package com.example.androcrypto;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
// TODO: inutile
import com.example.androcrypto.databinding.ActivityMainBinding;
import com.example.androcrypto.databinding.DetailsActivityBinding;
import com.example.androcrypto.viewmodels.DetailsViewModel;
import com.example.androcrypto.viewmodels.IDetailsViewModel;
import com.example.androcrypto.viewmodels.IViewModel;
import com.example.androcrypto.viewmodels.RetrofitViewModel;

public class DetailsActivity extends AppCompatActivity {

    private DetailsActivityBinding binding;
    private IDetailsViewModel viewModel;

    private String uuid;
    //Name field to at least display the name if no connection is available
    private String name;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        uuid = intent.getStringExtra("COIN_UUID");

        viewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        viewModel.generateCoin(uuid);

        viewModel.getDataCoin().observe(this, coin -> {
            if(coin != null) {
                binding.coinName.setText(coin.getName());
                binding.coinSymbol.setText(coin.getSymbol());
                binding.coinPrice.setText(coin.getPrice());
                binding.coinDescription.setText(coin.getDescription());
            }
        });
    }
}
