package com.xw.permanentservice.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.xw.permanentservice.Utils;
import com.xw.permanentservice.service.ForegroundService;

/**
 * 屏幕唤醒、锁屏、解锁监听广播
 * <br/>
 * 作者：XW <br/>
 * 邮箱：xw_appdev@163.com <br/>
 * 时间：2017-03-02 23:49
 */

public class ScreenBroadcastReceiver extends BroadcastReceiver {

    String tag = "ScreenBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_SCREEN_ON)) {
            Log.d(tag, "屏幕点亮");
        } else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
            Log.d(tag, "屏幕锁定");
        } else if (action.equals(Intent.ACTION_USER_PRESENT)) {//只有这个可以静态注册广播
            Toast.makeText(context, "屏幕解锁", Toast.LENGTH_SHORT).show();
            if (Utils.isServiceRunning(context, ForegroundService.class.getName())) {
                Toast.makeText(context, ForegroundService.class.getName() + "正在运行...", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, ForegroundService.class.getName() + "已停止运行...", Toast.LENGTH_SHORT).show();
                Intent service = new Intent(context, ForegroundService.class);
                context.startService(service);
            }
        }
    }
}























