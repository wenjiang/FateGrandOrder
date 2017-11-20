package com.wenjiang.wenbiao.fategrandorder.application;

import android.app.Application;

import com.wenjiang.wenbiao.fategrandorder.BuildConfig;
import com.wenjiang.wenbiao.fategrandorder.crash.CrashManager;
import com.wenjiang.wenbiao.fategrandorder.database.database.DatabaseStore;
import com.wenjiang.wenbiao.fategrandorder.database.sp.SharedPreferencesManager;
import com.wenjiang.wenbiao.fategrandorder.log.Logger;
import com.wenjiang.wenbiao.fategrandorder.skin.SkinManager;
import com.wenjiang.wenbiao.fategrandorder.utils.SystemUtil;

/**
 * Created by wenbiao on 2017/10/20.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        CrashManager.getInstance().init(SystemUtil.getDeviceBrand());
        Logger.init();
        SharedPreferencesManager.init(this);
        Logger.debug(BuildConfig.LOG_DEBUG);
        SkinManager.getInstance().init(this);
//        SkinManager.getInstance().load();
        DatabaseStore.init(this);
    }
}
