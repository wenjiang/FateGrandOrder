package com.wenjiang.wenbiao.fategrandorder.view.viewcontainer;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;

import com.wenjiang.wenbiao.fategrandorder.R;
import com.wenjiang.wenbiao.fategrandorder.fragment.BaseFragment;
import com.wenjiang.wenbiao.fategrandorder.fragment.FragmentController;
import com.wenjiang.wenbiao.fategrandorder.fragment.LiveFragment;
import com.wenjiang.wenbiao.fategrandorder.fragment.MainFragment;
import com.wenjiang.wenbiao.fategrandorder.fragment.SettingFragment;

/**
 * Created by wenbiao on 2017/11/21.
 */

public final class MainFragmentViewController extends BaseViewController implements BaseFragment.DataObserverListener<String> {

    private FragmentController fragmentController;

    private MainFragmentViewController(Activity activity) {
        init(activity);
    }

    public static MainFragmentViewController newInstance(Activity activity) {
        return new MainFragmentViewController(activity);
    }

    @Override
    protected void init(Activity activity) {
        fragmentController = FragmentController.newInstance(R.id.fl_content, ((FragmentActivity) activity).getSupportFragmentManager());
        MainFragment mainFragment = MainFragment.newInstance("你好");
        LiveFragment liveFragment = new LiveFragment();
        SettingFragment settingFragment = new SettingFragment();
        fragmentController.add("Main", mainFragment);
        fragmentController.add("Activity", liveFragment);
        fragmentController.add("Setting", settingFragment);
        fragmentController.show("Main");
        mainFragment.setDataListener(this);
    }

    @Override
    public void setData(String data) {

    }

    @Override
    public String getData() {
        return null;
    }

    public void showFragment(String tag) {
        fragmentController.show(tag);
    }
}
