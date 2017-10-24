package com.wenjiang.wenbiao.fategrandorder.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;

import com.example.wenbiao.fategrandorder.R;
import com.wenjiang.wenbiao.fategrandorder.constant.Constant;
import com.wenjiang.wenbiao.fategrandorder.utils.SettingUtils;
import com.wenjiang.wenbiao.fategrandorder.view.FloatView;

public class MainActivity extends AppCompatActivity {
    private FloatView floatView;
    private AccessibilityReceiver receiver;
    private IntentFilter intentFilter;
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatView = new FloatView(this);

        receiver = new AccessibilityReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.BroadcastAction.ACCESSIBILITY_BROADCAST_ACTION);
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
