package com.wenjiang.wenbiao.fategrandorder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.wenbiao.fategrandorder.R;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private WindowManager mWindowManager;
    private static FloatView mWholeView;
    private BackgroundService.IMyBinder iMyBinder;
    private List<String> packageList = new ArrayList<>();
    private MyServiceConnection connection;
    private String[] command = {"adb", "shell", "dumpsys", "activity", "activities"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);
        mWindowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        mWholeView = new FloatView(this);

        initData();

//        connection = new MyServiceConnection();
//        bindService(new Intent(this, BackgroundService.class), connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    private void initData() {
        packageList.clear();
        packageList.add("com.lifesense.LSWearable.intl.qa");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public static void show() {
        mWholeView.showView();
    }

    public static void dismiss() {
        mWholeView.removeView();
    }

    public class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            iMyBinder = (BackgroundService.IMyBinder) iBinder;
            iMyBinder.setPackageNames(packageList);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    public String getResult() {
        ShellExecute cmdexe = new ShellExecute();
        String result = "";
        try {
            result = cmdexe.execute(command, "/");
        } catch (IOException e) {
            Log.e("tag", "IOException");
            e.printStackTrace();
        }
        return result;
    }

    private class ShellExecute {
        /*
         * args[0] : shell 命令 如"ls" 或"ls -1";
         * args[1] : 命令执行路径 如"/" ;
         */
        public String execute(String[] cmmand, String directory)
                throws IOException {
            String result = "";
            try {
                ProcessBuilder builder = new ProcessBuilder(cmmand);
                if (directory != null)
                    builder.directory(new File(directory));
                builder.redirectErrorStream(true);
                Process process = builder.start();
                //得到命令执行后的结果
                InputStream is = process.getInputStream();
                byte[] buffer = new byte[1024];
                while (is.read(buffer) != -1) {
                    result = result + new String(buffer);
                }
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }
    }
}
