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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Coin coin);

    @Query("SELECT * FROM coin_table")
    LiveData<List<Coin>> getAll();
}