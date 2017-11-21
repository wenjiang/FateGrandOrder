package com.wenjiang.wenbiao.fategrandorder.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wenjiang.wenbiao.fategrandorder.R;
import com.wenjiang.wenbiao.fategrandorder.log.Logger;

/**
 * Created by wenbiao on 2017/10/24.
 */

public class LiveFragment extends BaseFragment {
    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInVisible() {

    }

    @Override
    protected void onFirstVisible() {

    }

    @Override
    public Bundle saveState() {
        return null;
    }

    @Override
    public void restoreState(Bundle bundle) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_live, null);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
