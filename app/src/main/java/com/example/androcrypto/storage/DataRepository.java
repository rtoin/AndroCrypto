package com.example.androcrypto.storage;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.androcrypto.models.Coin;

import java.util.List;

public class DataRepository {

    private final CustomDao customDao;
    private final LiveData<List<Coin>> data;

    public DataRepository(Context applicationContext) {
        AppDatabase database = AppDatabase.getDatabase(applicationContext);
        this.customDao = database.customDaoDao();
        this.data = customDao.getAll();
    }

    public LiveData<List<Coin>> getData() {
        return data;
    }

    public void insertData(Coin coin) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            customDao.insert(coin);
        });
    }
}
