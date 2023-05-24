package com.dami.calculadora.ui.base;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavDeepLink;
import androidx.navigation.NavDeepLinkBuilder;

import com.dami.calculadora.R;

import java.util.Random;

public class BaseFragment extends Fragment {

    public void showNotification(String message, int currentFragmentID, String CHANNEL_ID) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            PendingIntent pendingIntent = new NavDeepLinkBuilder(getContext())
                    .setGraph(R.navigation.nav_graph)
                    .setDestination(currentFragmentID)
                    .createPendingIntent();
            Notification.Builder builder = new Notification.Builder(getContext(), CHANNEL_ID);
            builder.setContentIntent(pendingIntent);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle(getString(R.string.app_name));
            builder.setContentText(message);
            builder.setAutoCancel(true);

            NotificationManager notificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(new Random().nextInt(), builder.build());
        }
    }

}
