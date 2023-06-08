package com.example.androcrypto;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
// TODO: inutile
import com.example.androcrypto.databinding.ActivityMainBinding;
import com.example.androcrypto.databinding.DetailsActivityBinding;
// TODO: attention, peu de temps à la fin du module pour terminer le projet. Cette classe devrait être terminée
public class DetailsActivity extends AppCompatActivity {

    private DetailsActivityBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

}
