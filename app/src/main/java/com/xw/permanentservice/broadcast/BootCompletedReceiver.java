package com.xw.permanentservice.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.xw.permanentservice.service.ForegroundService;

/**
 * 开机启动广播接收器 <br/>
 * 需添加权限:android.permission.RECEIVE_BOOT_COMPLETED
 * <br/>
 * 作者：XW <br/>
 * 邮箱：xw_appdev@163.com <br/>
 * 时间：2017-03-03 00:15
 */

public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Toast.makeText(context, "系统开机", Toast.LENGTH_SHORT).show();
            //启动服务
            Intent service = new Intent(context, ForegroundService.class);
            context.startService(service);
        }
    }
}






















