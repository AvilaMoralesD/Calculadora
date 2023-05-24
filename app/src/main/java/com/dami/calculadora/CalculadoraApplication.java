package com.dami.calculadora;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.dami.calculadora.data.database.CalculadoraDatabase;

public class CalculadoraApplication extends Application {
    public static final String CHANNEL_ID = "MY_CHANNEL";
    @Override
    public void onCreate() {
        super.onCreate();
        CalculadoraDatabase.create(getApplicationContext());
        createNotificationChannel();
    }

private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Canal Notificacion"; //TODO Extraer
            String description = "Descripción canal"; //TODO Extraer
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
