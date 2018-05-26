package com.zkhz.a3rdlibsdemo.notification;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper {


    private static final String PRIMARY_CHANNEL = "default";
    private static final String SECONDARY_CHANNEL = "second";
    private NotificationManager manager;

    public NotificationHelper(Context base) {
        super(base);


    }

    /**
     * Notification 对象必须包含:小图标  标题   详细文本
     */
    public NotificationCompat.Builder getNotifyBuilder(Context context,String title, String body) {

        Intent resultIntent = new Intent(context, ResultActivity1.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
        stackBuilder.addParentStack(ResultActivity1.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent pendingIntent =stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        return new NotificationCompat.Builder(getApplicationContext(), PRIMARY_CHANNEL)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(getSmallIcon())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
    }

    private int getSmallIcon() {
        return android.R.drawable.stat_notify_chat;
    }


    public void notify(int id, NotificationCompat.Builder builder) {

        getManager().notify(id, builder.build());

    }

    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }
}
