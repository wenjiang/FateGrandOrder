package com.wenjiang.wenbiao.fategrandorder.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wenjiang.wenbiao.fategrandorder.R;

/**
 * Created by wenbiao on 2017/10/24.
 */

public class MainFragment extends BaseFragment {
    private final String TAG = "MainFragment";

    @BindView(id = R.id.text)
    private TextView textView;
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
        Log.e(TAG, "用户看到了");
    }

    @Override
    protected void onUserInVisible() {
        Log.e(TAG, "用户看不到了");
    }

    @Override
    protected void onFirstVisible() {
        Log.e(TAG, "第一次看到了");
        try {
            textView.setText("哈哈");
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
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
        Log.e(TAG, name);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentInject.newInstance().injectValue(this);
        Log.e(TAG, "创建MainFragment" + name);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String data = (String) listener.getData();
        Log.e("tag", data);
        listener.setData("Hello, Fragment");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, null);
        FragmentInject.newInstance().injectView(view, this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.name = "哈哈";
        Log.e(TAG, "MainFragment被销毁");
    }
}
