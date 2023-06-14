package com.example.androcrypto;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androcrypto.databinding.CoinCellBinding;
import com.example.androcrypto.models.Coin;
import com.example.androcrypto.storage.Preferences;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Coin> data;
    private Listener listener;

    public RecyclerViewAdapter(List<Coin> coins) {
        this.data = coins;
    }

    public Listener getListener() {
        return listener;
    }

    public void setListener(Listener listener) {
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

        String currentFavorite = Preferences.getInstance().getFavoriteCoin();
        if(currentFavorite.equals(data.get(position).getName())) {
            holder.binding.iconFavorite.setVisibility(View.VISIBLE);
        } else {
            holder.binding.iconFavorite.setVisibility(View.INVISIBLE);
        }

        holder.binding.getRoot().setOnClickListener(v -> {
            if(listener != null) {
                listener.onClick(data.get(position));
            }
        });

        holder.binding.textName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null) {
                    listener.onClick(data.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(data != null) {
            return data.size();
        } else {
            return 0;
        }
    }

    public void setData(List<Coin> coins) {
        this.data = coins;
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private CoinCellBinding binding;

        public MyViewHolder(CoinCellBinding coinCellBinding) {
            super(coinCellBinding.getRoot());
            this.binding = coinCellBinding;
        }
    }
}
