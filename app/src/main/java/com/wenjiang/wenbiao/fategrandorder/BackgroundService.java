package com.wenjiang.wenbiao.fategrandorder;

import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.jaredrummler.android.processes.AndroidProcesses;
import com.jaredrummler.android.processes.models.AndroidAppProcess;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

/**
 * Created by wenbiao on 2017/10/20.
 */

public class BackgroundService extends Service {
    private List<String> packageList = new ArrayList<>();
    private static final String TAG = "WatchDogService";
    private static boolean flag = true;// 线程退出的标记
    private ActivityManager am;

    @Override
    public void onCreate() {
        super.onCreate();
        // 服务一旦启动要在后台监视任务栈最顶端应用
        am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

//        new Thread() {
//            @RequiresApi(api = Build.VERSION_CODES.M)
//            @Override
//            public void run() {
//                super.run();
////                while (flag) {
////                    synchronized (BackgroundService.class) {
////                        List<AndroidAppProcess> processes = AndroidProcesses.getRunningAppProcesses();
////                        for(AndroidAppProcess process : processes){
////                            Log.e("tag", process.getPackageName());
////                        }
////                        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
////                        List<ActivityManager.RunningAppProcessInfo> appProcessList = mActivityManager
////                                .getRunningAppProcesses();
//////                        PackageManager pm = getPackageManager();
////                        List<ActivityManager.AppTask> runningTaskInfos = mActivityManager.getAppTasks();
////                        for(ActivityManager.AppTask info : runningTaskInfos){
////                            Log.e("tag", info.getTaskInfo().topActivity.getClassName());
////                        }
//////                        for (ActivityManager.RunningAppProcessInfo appProcess : appProcessList) {
//////                            Log.e("TAG", appProcess.processName);
//////                            int pid = appProcess.pid; // pid
//////                            String processName = appProcess.processName; // 进程名
//////                            Log.i(TAG, "processName: " + processName + "  pid: " + pid);
//////
//////                            String[] pkgNameList = appProcess.pkgList; // 获得运行在该进程里的所有应用程序包
//////
//////                            // 输出所有应用程序的包名
//////                            for (int i = 0; i < pkgNameList.length; i++) {
//////                                String pkgName = pkgNameList[i];
//////                                if(pkgName.contains("com.lifesense.LSWearable.intl.qa")){
//////                                    MainActivity.show();
//////                                }
//////                            }
//////                        }
////                        SystemClock.sleep(500);
////                    }
////                    Log.i(TAG, "服务在循环");
////                }
////            }
//        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flag = false;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public class MyBinder extends Binder implements IMyBinder {

        @Override
        public void setPackageNames(List<String> packageNames) {
            packageList.clear();
            packageList.addAll(packageNames);
        }
    }

    MyBinder myBinder = new MyBinder();

    public interface IMyBinder {
        void setPackageNames(List<String> packageNames);
    }
}


