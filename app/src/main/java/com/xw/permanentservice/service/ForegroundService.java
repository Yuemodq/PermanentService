package com.xw.permanentservice.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.xw.permanentservice.R;

/**
 * <br/>
 * 作者：XW <br/>
 * 邮箱：xw_appdev@163.com <br/>
 * 时间：2017-03-02 22:11
 */

public class ForegroundService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "前台服务启动...", Toast.LENGTH_SHORT).show();
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        Notification n = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Ticker")
                .setContentTitle("Title")
                .setWhen(System.currentTimeMillis())
//                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, n);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "前台服务销毁...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ForegroundService.class);
        startService(intent);
    }
}

























