package com.example.androcrypto.storage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.androcrypto.models.Coin;

import java.util.List;

@Dao
public interface CustomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Coin coin);

    @Query("SELECT * FROM coin_table ORDER BY CAST(price AS FLOAT) DESC")
    LiveData<List<Coin>> getAll();
}