package com.oleksandr.mytodolist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by duo on 8/13/2015.
 */
public class OverDueItemReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {

        Intent startMainActivityIntent = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, startMainActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification n = new Notification.Builder(context)
                .setContentTitle("You missed ToDoItem")
                .setContentText("ToDoItem title")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pIntent)
                .setAutoCancel(true).build();

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, n);
    }
}