package com.example.androcrypto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.androcrypto.databinding.DetailsActivityBinding;
import com.example.androcrypto.models.Coin;
import com.example.androcrypto.storage.PreferencesHelper;
import com.example.androcrypto.viewmodels.DetailsViewModel;
import com.example.androcrypto.viewmodels.IDetailsViewModel;

public class DetailsActivity extends AppCompatActivity {

    private DetailsActivityBinding binding;
    private IDetailsViewModel viewModel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String uuid = intent.getStringExtra("COIN_UUID");

        viewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        viewModel.generateCoin(uuid);

        binding.buttonFavorite.setEnabled(false);

        viewModel.getDataCoin().observe(this, coin -> {
            if(coin != null) {
                binding.coinName.setText(coin.getName());
                binding.coinSymbol.setText(coin.getSymbol());
                binding.coinPrice.setText(coin.getPrice());
                binding.coinDescription.setText(coin.getDescription());
            }

            updateFavoriteDisplay(coin);

            binding.buttonFavorite.setOnClickListener(v -> {
                updateFavoriteCoin(coin);
                updateFavoriteDisplay(coin);
            });
        });
    }

    /**
     * Update the text of the favorite button and the visibility of the favorite star
     *
     * @param coin coin compared to the value stored in shared preferences as favorite
     */
    private void updateFavoriteDisplay(Coin coin) {

        String currentFavorite = PreferencesHelper.getInstance().getFavoriteCoin();

        if(currentFavorite != null) {
            if(coin != null) {
                if(currentFavorite.equals(coin.getName())) {
                    binding.buttonFavorite.setText("Remove from favorite");
                    binding.iconStar.setVisibility(View.VISIBLE);
                } else {
                    binding.buttonFavorite.setText("Set as favorite");
                    binding.iconStar.setVisibility(View.INVISIBLE);
                }
            }
        } else {
            binding.buttonFavorite.setText("Set as favorite");
            binding.iconStar.setVisibility(View.INVISIBLE);
        }

        binding.buttonFavorite.setEnabled(true);
    }

    /**
     * Update the coin stored as favorite in shared preferences
     *
     * @param coin coin to save as favorite
     */
    private void updateFavoriteCoin(Coin coin) {
        String currentFavorite = PreferencesHelper.getInstance().getFavoriteCoin();

        if(currentFavorite != null) {
            if(coin != null) {
                if(currentFavorite.equals(coin.getName())) {
                    PreferencesHelper.getInstance().setFavoriteCoin("");
                } else {
                    PreferencesHelper.getInstance().setFavoriteCoin(coin.getName());
                }
            }
        } else {
            PreferencesHelper.getInstance().setFavoriteCoin(coin.getName());
        }
    }
}
