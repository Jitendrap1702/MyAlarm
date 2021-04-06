package com.example.myalarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    MediaPlayer mp;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());
//        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
//            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
//            //Intent intent = new Intent(this, AlertReceiver.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            alarmManager.cancel(pendingIntent);
//        }

//        mp=MediaPlayer.create(context, R.raw.alarm);
//        mp.start();
//        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
//
//        Toast.makeText(context, "Alarm worked.", Toast.LENGTH_LONG).show();
//        int icon = R.drawable.ic_launcher_background;
//        CharSequence tickerText = "Hello you have to take medicine I am Nitin Sharma";
//        long when = System.currentTimeMillis();
//
//        CharSequence contentTitle = "My notification";
//        CharSequence contentText = "Hello World!";
//
//        final int NOTIF_ID = 1234;
//        NotificationManager notofManager = (NotificationManager)context. getSystemService(Context.NOTIFICATION_SERVICE);
//        Intent notificationIntent = new Intent(context, Alset.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(context,1, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        //Notification notification = new Notification(icon, tickerText,when );
//        Notification notification = new Notification(icon,tickerText, when);
////        notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
//        notification.flags = Notification.FLAG_INSISTENT;
//        notification.defaults |= Notification.DEFAULT_SOUND;
//        notofManager.notify(NOTIF_ID,notification);
//
//        Toast.makeText(context, "Alarm worked.", Toast.LENGTH_LONG).show();
//        Intent i = new Intent(context,Alset.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(i);
    }
}
