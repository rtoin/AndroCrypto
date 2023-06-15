package com.example.androcrypto;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.androcrypto.models.Coin;
import com.example.androcrypto.storage.PreferencesHelper;
import com.example.androcrypto.viewmodels.DetailsViewModel;
import com.example.androcrypto.viewmodels.IDetailsViewModel;

public class NotificationService extends Service {

    public static boolean isRunning;

    private static final int NOTIFICATION_ID = 1001;
    private static final String CHANNEL_ID = "Favorite channel";
    private static final int REFRESH_DELAY = 10000;

    private HandlerThread handlerThread;
    private Handler handler;

    private IDetailsViewModel viewModel;

    private String uuid;

    private int counter;

    public NotificationService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handlerThread = new HandlerThread("service_thread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        handler = new Handler(looper);

        viewModel = new DetailsViewModel(this.getApplication());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isRunning = true;
        setupForeground();
        startMonitoring();
        return Service.START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        isRunning = false;
        handlerThread.quit();
        super.onDestroy();
    }

    private void setupForeground() {
        createChannel();

        startForeground(NOTIFICATION_ID, createNotification("Favorite a currency to see it there"));
    }

    private void startMonitoring() {
        handler.post(createRunnable());

        Observer<Coin> observer = coin -> {
            if (coin != null) {
                NotificationManager notificationManager = this.getSystemService(NotificationManager.class);
                notificationManager.notify(NOTIFICATION_ID, createNotification(coin.getName() + ": " + coin.getPrice() + " US$"));
            }
        };
        viewModel.getDataCoin().observeForever(observer);
    }

    private void createChannel() {
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "favCoin", NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);

        NotificationManager notificationManager = this.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    private Runnable createRunnable() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                uuid = PreferencesHelper.getInstance().getFavoriteCoinUuid();
                viewModel.generateCoin(uuid);
                handler.postDelayed(this, REFRESH_DELAY);
            }
        };
        return runnable;
    }

    private Notification createNotification(String text) {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.star)
                .setContentTitle("My favorite crypto currency")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setSilent(true)
                .build();

        return notification;
    }
}
