package com.wenjiang.wenbiao.fategrandorder.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wenjiang.wenbiao.fategrandorder.R;
import com.wenjiang.wenbiao.fategrandorder.constant.Constant;
import com.wenjiang.wenbiao.fategrandorder.fragment.BaseFragment;
import com.wenjiang.wenbiao.fategrandorder.fragment.FragmentController;
import com.wenjiang.wenbiao.fategrandorder.fragment.LiveFragment;
import com.wenjiang.wenbiao.fategrandorder.fragment.MainFragment;
import com.wenjiang.wenbiao.fategrandorder.fragment.SettingFragment;
import com.wenjiang.wenbiao.fategrandorder.skin.ISkinUpdate;
import com.wenjiang.wenbiao.fategrandorder.skin.SkinManager;
import com.wenjiang.wenbiao.fategrandorder.utils.SettingUtils;
import com.wenjiang.wenbiao.fategrandorder.view.FloatView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements FloatView.OnFloatViewClickListener, View.OnClickListener, BaseFragment.DataObserverListener<String>,
        ISkinUpdate{
    private FloatView floatView;
    private AccessibilityReceiver receiver;
    private IntentFilter intentFilter;
    private final String TAG = "MainActivity";
    private MainFragment mainFragment;
    private LiveFragment liveFragment;
    private SettingFragment settingFragment;
    private FragmentController fragmentController;
    private ViewPager viewPager;

    @Override
    public void onThemeUpdate() {
        super.onThemeUpdate();
        float dimension = SkinManager.getInstance().getDimension(R.dimen.int_name);
        int color = SkinManager.getInstance().getColor(R.color.color_name);
        ((TextView)findViewById(R.id.tv)).setTextColor(color);
        ((TextView)findViewById(R.id.tv)).setTextSize(dimension);
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentController = FragmentController.newInstance(R.id.fl_content, getSupportFragmentManager());
        mainFragment = MainFragment.newInstance("你好");
        liveFragment = new LiveFragment();
        settingFragment = new SettingFragment();
        fragmentController.recreateFragments(mainFragment, liveFragment);
        fragmentController.setPreLoadFragment(liveFragment);
        fragmentController.show(mainFragment);
        mainFragment.setDataListener(this);
//        viewPager = (ViewPager) findViewById(R.id.vp_content);
//        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
//        adapter.addFragment(mainFragment);
//        adapter.addFragment(liveFragment);
//        adapter.addFragment(settingFragment);
//        viewPager.setAdapter(adapter);
//        viewPager.setCurrentItem(0);
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_first:
                fragmentController.show(mainFragment);
//                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_second:
                fragmentController.show(liveFragment);
                requestPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 1);
//                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_third:
                fragmentController.show(settingFragment);
//                try {
//                    IconManager.getInstance(this).enableComponent("test");
//                }catch (Exception e){
//                    Logger.e(e.toString());
//                }
                SkinManager.getInstance().restoreDefaultTheme();
//                viewPager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    @Override
    public void setData(String data) {
        Log.e(TAG, data);
    }

    @Override
    public String getData() {
        return "Hello, World";
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

    class MyAdapter extends FragmentStatePagerAdapter {
        private List<Fragment> fragmentList;

        public MyAdapter(FragmentManager fm) {
            super(fm);
            this.fragmentList = new ArrayList<>();
        }

        public void addFragment(Fragment fragment) {
            fragmentList.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
