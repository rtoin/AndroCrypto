package com.example.androcrypto;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androcrypto.databinding.CoinCellBinding;
import com.example.androcrypto.models.Coin;
import com.example.androcrypto.storage.PreferencesHelper;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Coin> data;
    private CoinListener listener;

    public RecyclerViewAdapter(List<Coin> coins) {
        this.data = coins;
    }

    public CoinListener getListener() {
        return listener;
    }

    public void setListener(CoinListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder(CoinCellBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.textName.setText(data.get(position).getName());
        holder.binding.textPriceValue.setText(data.get(position).getPrice());

        String currentFavorite = PreferencesHelper.getInstance().getFavoriteCoin();
        if (currentFavorite.equals(data.get(position).getName())) {
            holder.binding.iconFavorite.setVisibility(View.VISIBLE);
        } else {
            holder.binding.iconFavorite.setVisibility(View.INVISIBLE);
        }

        holder.binding.getRoot().setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(data.get(position));
            }
        });

        holder.binding.textName.setOnClickListener(view -> {
            if (listener != null) {
                listener.onClick(data.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    public void setData(List<Coin> coins) {
        this.data = coins;
        notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final CoinCellBinding binding;

        public MyViewHolder(CoinCellBinding coinCellBinding) {
            super(coinCellBinding.getRoot());
            this.binding = coinCellBinding;
        }
    }
}
