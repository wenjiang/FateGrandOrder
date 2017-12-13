package com.wenjiang.wenbiao.fategrandorder.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.wenjiang.wenbiao.fategrandorder.R;
import com.wenjiang.wenbiao.fategrandorder.skin.ISkinUpdate;
import com.wenjiang.wenbiao.fategrandorder.view.viewcontainer.BottomViewController;
import com.wenjiang.wenbiao.fategrandorder.view.viewcontainer.FloatViewController;
import com.wenjiang.wenbiao.fategrandorder.view.viewcontainer.TitleBarViewController;

public class MainActivity extends BaseActivity implements View.OnClickListener,
        ISkinUpdate {
    private FloatViewController floatViewController;

    @Override
    public void onThemeUpdate() {
        super.onThemeUpdate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TitleBarViewController titleBarViewController = TitleBarViewController.newInstance(this);
        BottomViewController bottomViewController = BottomViewController.newInstance(this);
        floatViewController = FloatViewController.newInstance(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        floatViewController.unregisterReceiver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        floatViewController.registerReceiver(this);
//        if (!SettingUtil.isAccessibilitySettingsOn(this)) {
//            //监测是否有打开辅助功能
//            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
//            startActivity(intent);
//        }
    }

    @Override
    public void onClick(View view) {
    }
}
