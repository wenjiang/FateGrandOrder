package com.wenjiang.wenbiao.fategrandorder.application;

import android.app.Application;

import com.wenjiang.wenbiao.fategrandorder.BuildConfig;
import com.wenjiang.wenbiao.fategrandorder.database.sp.SharedPreferencesManager;
import com.wenjiang.wenbiao.fategrandorder.log.Logger;
import com.wenjiang.wenbiao.fategrandorder.skin.SkinManager;

/**
 * Created by wenbiao on 2017/10/20.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.init();
        Logger.debug(BuildConfig.LOG_DEBUG);
        SkinManager.getInstance().init(this);
        SkinManager.getInstance().load();
        SharedPreferencesManager.init(this);
    }
}
