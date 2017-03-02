package com.xw.permanentservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.xw.permanentservice.Utils;
import com.xw.permanentservice.aidl.IDeamonInterface;

/**
 * <br/>
 * 作者：XW <br/>
 * 邮箱：xw_appdev@163.com <br/>
 * 时间：2017-03-03 01:42
 */

public class DeamonService1 extends Service {

    private String tag = "DeamonService1";

    private String startProcessName = "com.xw.service:remote2";

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "守护进程 1 创建...", Toast.LENGTH_SHORT).show();
        keepDeamonService2();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onTrimMemory(int level) {
        Toast.makeText(this, "守护进程 1 在被系统整理内存...", Toast.LENGTH_SHORT).show();
        keepDeamonService2();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "守护进程 1 销毁...", Toast.LENGTH_SHORT).show();
    }

    private final IDeamonInterface.Stub binder = new IDeamonInterface.Stub() {

        @Override
        public void startService() throws RemoteException {
            Intent intent = new Intent(getApplicationContext(), DeamonService2.class);
            getApplicationContext().startService(intent);
        }

        @Override
        public void stopService() throws RemoteException {
            Intent intent = new Intent(getApplicationContext(), DeamonService2.class);
            getApplicationContext().stopService(intent);
        }
    };

    private void keepDeamonService2() {
        boolean isRunning = Utils.isProcessRunning(getApplicationContext(), startProcessName);
        if (!isRunning) {
            Toast.makeText(this, "重新启动守护进程 2...", Toast.LENGTH_SHORT).show();
            try {
                binder.startService();
            } catch (RemoteException e) {
                Log.e(tag, e.getMessage());
            }
        } else {
            Toast.makeText(this, "守护进程 2 正在运行...", Toast.LENGTH_SHORT).show();
        }
    }
}





















