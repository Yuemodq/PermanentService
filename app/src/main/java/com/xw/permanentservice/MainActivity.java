package com.xw.permanentservice;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xw.permanentservice.broadcast.ScreenBroadcastReceiver;
import com.xw.permanentservice.service.DeamonService1;
import com.xw.permanentservice.service.DeamonService2;
import com.xw.permanentservice.service.ForegroundService;

public class MainActivity extends AppCompatActivity {

    ScreenBroadcastReceiver screenReceiver;


    /***
     * 
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerScreenReceiver();
    }

    private void registerScreenReceiver() {
        screenReceiver = new ScreenBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        registerReceiver(screenReceiver, filter);
    }

    //启动前台服务
    public void startForeground(View view) {
//        Intent intent = new Intent(this, ForegroundService.class);
//        startService(intent);
        start(ForegroundService.class);
    }

    //启动守护进程
    public void startDeamon(View view) {
        //启动守护进程1
        start(DeamonService1.class);
        //启动守护进程2
        start(DeamonService2.class);
        //
    }

    private void start(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(screenReceiver);
    }
}
