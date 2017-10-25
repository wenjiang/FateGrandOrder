package com.wenjiang.wenbiao.fategrandorder.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import com.example.wenbiao.fategrandorder.R;
import com.wenjiang.wenbiao.fategrandorder.constant.Constant;
import com.wenjiang.wenbiao.fategrandorder.fragment.FragmentController;
import com.wenjiang.wenbiao.fategrandorder.fragment.LiveFragment;
import com.wenjiang.wenbiao.fategrandorder.fragment.MainFragment;
import com.wenjiang.wenbiao.fategrandorder.fragment.SettingFragment;
import com.wenjiang.wenbiao.fategrandorder.utils.SettingUtils;
import com.wenjiang.wenbiao.fategrandorder.view.FloatView;

public class MainActivity extends FragmentActivity implements FloatView.OnFloatViewClickListener, View.OnClickListener {
    private FloatView floatView;
    private AccessibilityReceiver receiver;
    private IntentFilter intentFilter;
    private final String TAG = "MainActivity";
    private MainFragment mainFragment;
    private LiveFragment liveFragment;
    private SettingFragment settingFragment;
    private FragmentController fragmentController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentController = FragmentController.newInstance(R.id.fl_content, getSupportFragmentManager());
        mainFragment = new MainFragment();
        liveFragment = new LiveFragment();
        settingFragment = new SettingFragment();
        fragmentController.show(mainFragment);
        floatView = new FloatView(this);
        floatView.setOnFloatViewClickListener(this);
        receiver = new AccessibilityReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.BroadcastAction.ACCESSIBILITY_BROADCAST_ACTION);

        findViewById(R.id.tv_first).setOnClickListener(this);
        findViewById(R.id.tv_second).setOnClickListener(this);
        findViewById(R.id.tv_third).setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
    }

    private void showView() {
        floatView.showView();
    }

    private void dismissView() {
        floatView.removeView();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver, intentFilter);
        if (!SettingUtils.isAccessibilitySettingsOn(this)) {
            //监测是否有打开辅助功能
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
        }
    }

    @Override
    public void onFloatViewClick() {
        //TODO 点击展开菜单
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_first:
                fragmentController.show(mainFragment);
                break;
            case R.id.tv_second:
                fragmentController.show(liveFragment);
                break;
            case R.id.tv_third:
                fragmentController.show(settingFragment);
                break;
            default:
                break;
        }
    }

    public class AccessibilityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isFgoOpen = intent.getBooleanExtra(Constant.IntentKey.INTENT_KEY_ACCESSIBILITY, false);
            if (isFgoOpen) {
                //收到FGO应用被打开的消息
                showView();
            } else {
                //收到FGO应用退到后台的消息
                dismissView();
            }
        }
    }
}
