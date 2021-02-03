package com.cpb_dev.bluecloudjournal;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;

public class NotifyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate(){
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent = new Intent(this.getApplicationContext(), SettingsPage.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        Notification notify = new Notification.Builder(this)
                .setContentTitle("Make a New Journal Entry!")
                .setContentText("Open Blue Cloud Journal to create a new entry!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();

        notificationManager.notify(1, notify);
    }
}
