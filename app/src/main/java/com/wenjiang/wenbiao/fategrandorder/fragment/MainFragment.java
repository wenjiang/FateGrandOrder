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

public class MainFragment extends BaseFragment {
    private final String TAG = "MainFragment";

    @Override
    protected void onUserVisible() {
        Log.e(TAG, "用户看到了");
    }

    @Override
    protected void onUserInVisible() {
        Log.e(TAG, "用户看不到了");
    }

    @Override
    protected void onFirstVisible() {
        Log.e(TAG, "第一次看到了");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "创建MainFragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        return view;
    }
}
