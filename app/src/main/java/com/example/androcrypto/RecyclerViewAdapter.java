package com.example.androcrypto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androcrypto.databinding.CoinCellBinding;
import com.example.androcrypto.models.Coin;

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding.nameView.setText(data.get(position).getName());
        holder.binding.priceView.setText(data.get(position).getPrice());


        holder.binding.getRoot().setOnClickListener(v -> {
            // TODO: attention si listener == null
            listener.onClick(data.get(position));
        });
        holder.binding.nameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: pourquoi ne pas utiliser position comme au dessus ?
                listener.onClick(data.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        // TODO: attention si data == null
        return data.size();
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
