package com.wenjiang.wenbiao.fategrandorder.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.wenjiang.wenbiao.fategrandorder.R;
import com.wenjiang.wenbiao.fategrandorder.log.Logger;
import com.wenjiang.wenbiao.fategrandorder.view.AutoTextSizeManager;

/**
 * Created by wenbiao on 2017/10/24.
 */

public class MainFragment extends BaseFragment {
    private final String TAG = "MainFragment";

    @Args
    private String name;

    public static MainFragment newInstance(String name) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

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
        Bundle bundle = new Bundle();
        bundle.putString("key", "哈哈");
        return bundle;
    }

    @Override
    public void restoreState(Bundle bundle) {
        String name = bundle.getString("key");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentInject.newInstance().injectValue(this);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String data = (String) listener.getData();
        listener.setData("Hello, Fragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        AutoTextSizeManager.getInstance().setViewGroup((LinearLayout) view.findViewById(R.id.ll_header));
        FragmentInject.newInstance().injectView(view, this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.name = "哈哈";
    }
}
