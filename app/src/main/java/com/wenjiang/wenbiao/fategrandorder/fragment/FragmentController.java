package com.wenjiang.wenbiao.fategrandorder.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by wenbiao on 2017/10/25.
 */

public final class FragmentController {
    private FragmentManager fragmentManager;
    private String preFragmentTag;
    private int rootViewId;

    private FragmentController(int rootViewId, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.rootViewId = rootViewId;
    }

    public static FragmentController newInstance(int rootViewId, FragmentManager fragmentManager) {
        return new FragmentController(rootViewId, fragmentManager);
    }

    public void show(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!TextUtils.isEmpty(preFragmentTag)) {
            transaction.hide(fragmentManager.findFragmentByTag(preFragmentTag));
        }
        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            transaction.add(rootViewId, fragment, fragment.getClass().getName());
        }

        preFragmentTag = fragment.getClass().getName();
        transaction.commitAllowingStateLoss();
    }
}