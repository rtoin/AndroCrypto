package com.example.androcrypto;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androcrypto.databinding.ActivityMainBinding;
import com.example.androcrypto.databinding.DetailsActivityBinding;

public class DetailsActivity extends AppCompatActivity {

    private DetailsActivityBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailsActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

}
