package com.example.mvvmexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.mvvmexample.databinding.ActivityMainBinding;
import com.example.mvvmexample.viewmodels.IViewModel;
import com.example.mvvmexample.viewmodels.MainViewModel;
import com.example.mvvmexample.viewmodels.OkHttpViewModel;
import com.example.mvvmexample.viewmodels.RetrofitViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private IViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.nextValueButton.setOnClickListener(v -> {
            viewModel.generateNextValue();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getData().observe(this, sampleModel -> {
            binding.dataTextView.setText(sampleModel.getData());
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.getData().removeObservers(this);
    }
}