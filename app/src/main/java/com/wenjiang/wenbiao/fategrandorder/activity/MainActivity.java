package com.wenjiang.wenbiao.fategrandorder.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wenjiang.wenbiao.fategrandorder.R;
import com.wenjiang.wenbiao.fategrandorder.skin.ISkinUpdate;
import com.wenjiang.wenbiao.fategrandorder.view.viewcontainer.BottomViewController;
import com.wenjiang.wenbiao.fategrandorder.view.viewcontainer.FloatViewController;
import com.wenjiang.wenbiao.fategrandorder.view.viewcontainer.MainFragmentViewController;
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

//        viewPager = (ViewPager) findViewById(R.id.vp_content);
//        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
//        adapter.addFragment(mainFragment);
//        adapter.addFragment(liveFragment);
//        adapter.addFragment(settingFragment);
//        viewPager.setAdapter(adapter);
//        viewPager.setCurrentItem(0);

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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
    }
}
