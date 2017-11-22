package com.wenjiang.wenbiao.fategrandorder.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.wenjiang.wenbiao.fategrandorder.R;
import com.wenjiang.wenbiao.fategrandorder.constant.Constant;
import com.wenjiang.wenbiao.fategrandorder.database.database.DatabaseStore;
import com.wenjiang.wenbiao.fategrandorder.log.Logger;
import com.google.gson.JsonArray;
import com.wenjiang.wenbiao.fategrandorder.network.CustomCallback;
import com.wenjiang.wenbiao.fategrandorder.network.HttpManager;
import com.wenjiang.wenbiao.fategrandorder.network.request.FateInfoRequest;
import com.wenjiang.wenbiao.fategrandorder.skin.ISkinUpdate;
import com.wenjiang.wenbiao.fategrandorder.view.FloatView;
import com.wenjiang.wenbiao.fategrandorder.view.viewcontainer.BottomViewController;
import com.wenjiang.wenbiao.fategrandorder.view.viewcontainer.MainFragmentViewController;
import com.wenjiang.wenbiao.fategrandorder.view.viewcontainer.TitleBarViewController;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends BaseActivity implements FloatView.OnFloatViewClickListener, View.OnClickListener,
        ISkinUpdate, BottomViewController.TextClickListener {
    private FloatView floatView;
    private AccessibilityReceiver receiver;
    private IntentFilter intentFilter;
    private ViewPager viewPager;
    private MainFragmentViewController mainFragmentViewController;

    @Override
    public void onThemeUpdate() {
        super.onThemeUpdate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FateInfoRequest request = HttpManager.getInstance().getAdapter().create(FateInfoRequest.class);
        request.getFateInfo(5, new CustomCallback<JsonArray>(){

            @Override
            public void success(JsonArray data, Response response) {
                super.success(data, response);
                Logger.json(data.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                super.failure(error);
                Logger.e(error.getMessage());
            }
        });

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

        TitleBarViewController titleBarViewController = TitleBarViewController.newInstance(this);
        BottomViewController bottomViewController = BottomViewController.newInstance(this);
        bottomViewController.setOnTextClickListener(this);
        mainFragmentViewController = MainFragmentViewController.newInstance(this);
        try {
            DatabaseStore.getInstance().showTableList();
        } catch (Exception e) {
            Logger.e(e.toString());
        }
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
//        if (!SettingUtil.isAccessibilitySettingsOn(this)) {
//            //监测是否有打开辅助功能
//            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
//            startActivity(intent);
//        }
    }

    @Override
    public void onFloatViewClick() {
        //TODO 点击展开菜单
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
    }

    @Override
    public void onLeftClick() {
        mainFragmentViewController.showMainFragment();
////                viewPager.setCurrentItem(0);
    }

    @Override
    public void onCenterClick() {
        mainFragmentViewController.showLiveFragment();
//                requestPermissions(new String[]{
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE
//                }, 1);
////                viewPager.setCurrentItem(1);
    }

    @Override
    public void onRightClick() {
        mainFragmentViewController.showSettingFragment();
////                try {
////                    IconManager.getInstance(this).enableComponent("test");
////                }catch (Exception e){
////                    Logger.e(e.toString());
////                }
//                SkinManager.getInstance().restoreDefaultTheme();
////                viewPager.setCurrentItem(2);
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

//    class MyAdapter extends FragmentStatePagerAdapter {
//        private List<Fragment> fragmentList;
//
//        public MyAdapter(FragmentManager fm) {
//            super(fm);
//            this.fragmentList = new ArrayList<>();
//        }
//
//        public void addFragment(Fragment fragment) {
//            fragmentList.add(fragment);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return fragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return fragmentList.size();
//        }
//    }
}
