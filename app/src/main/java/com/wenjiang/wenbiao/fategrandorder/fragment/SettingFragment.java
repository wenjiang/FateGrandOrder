package com.wenjiang.wenbiao.fategrandorder.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wenbiao.fategrandorder.R;

/**
 * Created by wenbiao on 2017/10/24.
 */

public class SettingFragment extends BaseFragment {
    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInVisible() {

    }

    @Override
    protected void onFirstVisible() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("tag", "创建SettingFragment");
    }
}
