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

    private MainFragment mainFragment;
    private LiveFragment liveFragment;
    private SettingFragment settingFragment;
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
        mainFragment = MainFragment.newInstance("你好");
        liveFragment = new LiveFragment();
        settingFragment = new SettingFragment();
        fragmentController.show(mainFragment);
        mainFragment.setDataListener(this);
    }

    @Override
    public void setData(String data) {

    }

    @Override
    public String getData() {
        return null;
    }

    public void showMainFragment(){
        fragmentController.show(mainFragment);
    }

    public void showLiveFragment(){
        fragmentController.show(liveFragment);
    }

    public void showSettingFragment(){
        fragmentController.show(settingFragment);
    }
}
