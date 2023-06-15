package com.example.androcrypto;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationHelper {

    private static String channelId;

    public static void createNotificationChannel(Context context, String id, String name, String description) {
        channelId = id;

        NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription(description);
        channel.enableLights(true);
        channel.setLightColor(Color.RED);

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    public static void showPersistentNotification(Context context, String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.star)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setOngoing(true);

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.notify(0, builder.build());
    }

    public static void removePersistentNotification(Context context) {
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.cancel(0);
    }
}
