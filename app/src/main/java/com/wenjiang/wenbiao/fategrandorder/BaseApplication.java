package com.wenjiang.wenbiao.fategrandorder;

import android.app.Application;
import android.content.Intent;

/**
 * Created by wenbiao on 2017/10/20.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, BackgroundService.class));
    }
}
