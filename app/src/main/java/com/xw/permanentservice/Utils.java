package com.xw.permanentservice;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * 工具类
 * <br/>
 * 作者：XW <br/>
 * 邮箱：xw_appdev@163.com <br/>
 * 时间：2017-03-03 00:31
 */

public class Utils {

//    static String tag = "Utils";

    /**
     * 检测Service是否运行
     * @param context 上下文环境
     * @param service 要检测的service全类名(建议使用:XXX.class.getName())
     * @return 如果运行，返回true；否则，返回false。
     */
    public static boolean isServiceRunning(Context context, String service) {
        boolean isRunning = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runServices = am.getRunningServices(60);
        if (runServices.size() <= 0) {
            return false;
        }
//        Log.d(tag, service);
        for (ActivityManager.RunningServiceInfo runService : runServices) {
            String name = runService.service.getClassName();
//            Log.d(tag, name);
            if (name.equals(service)) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * 检测相应进程是否正在运行
     * @param context 上下文环境
     * @param process 进程名
     * @return 如果相应进程正在运行，返回 true；否则，返回 false。
     */
    public static boolean isProcessRunning(Context context, String process) {
        boolean isRunning = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runProcesses = am.getRunningAppProcesses();
//        if (runProcesses.size() <= 0) {
//            return false;
//        }
        for (ActivityManager.RunningAppProcessInfo runProcess : runProcesses) {
            String name = runProcess.processName;
            if (name.equals(process)) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

}















