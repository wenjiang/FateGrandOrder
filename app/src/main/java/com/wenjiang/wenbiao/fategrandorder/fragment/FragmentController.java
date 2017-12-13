package com.wenjiang.wenbiao.fategrandorder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;

import com.wenjiang.wenbiao.fategrandorder.log.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wenbiao on 2017/10/25.
 */

public final class FragmentController {
    private FragmentManager fragmentManager;
    private String preFragmentTag;
    private int rootViewId;
    private List<String> deleteFragmentClazzList;
    private Map<String, Bundle> stateMap;
    private Map<String, Fragment> fragmentMap;

    private FragmentController(int rootViewId, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.rootViewId = rootViewId;
        this.deleteFragmentClazzList = new ArrayList<>();
        this.stateMap = new HashMap<>();
        this.fragmentMap = new HashMap<>();
    }

    public static FragmentController newInstance(int rootViewId, FragmentManager fragmentManager) {
        return new FragmentController(rootViewId, fragmentManager);
    }

    public void add(String tag, Fragment fragment){
        fragmentMap.put(tag, fragment);
    }

    public void recreateFragments(Fragment... fragments) {
        for (Fragment fragment : fragments) {
            deleteFragmentClazzList.add(fragment.getClass().getName());
        }
    }

    public void setPreLoadFragment(Fragment... fragments) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        for (Fragment fragment : fragments) {
            String clazz = fragment.getClass().getName();
            transaction.add(rootViewId, fragment, clazz);
            transaction.hide(fragment);
            if (isDeleteFragment(clazz)) {
                deleteFragmentClazzList.remove(clazz);
            }
        }

        transaction.commitAllowingStateLoss();
    }

    public void show(String tag) {
        Fragment fragment = fragmentMap.get(tag);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!TextUtils.isEmpty(preFragmentTag)) {
            Fragment preFragment = fragmentManager.findFragmentByTag(preFragmentTag);
            if (preFragment != null) {
                if (isDeleteFragment(preFragment.getClass().getName())) {
                    if (preFragment instanceof BaseFragment) {
                        Bundle bundle = ((BaseFragment) preFragment).saveState();
                        stateMap.put(preFragment.getClass().getName(), bundle);
                    }
                    transaction.remove(preFragment);
                } else {
                    transaction.hide(preFragment);
                }
            }
        }

        if (fragment.isAdded()) {
            transaction.show(fragment);
        } else {
            boolean isSaveState = stateMap.keySet().contains(fragment.getClass().getName());

            if (fragment instanceof BaseFragment && isSaveState) {
                Bundle bundle = stateMap.get(fragment.getClass().getName());
                ((BaseFragment) fragment).restoreState(bundle);
            }
            transaction.add(rootViewId, fragment, fragment.getClass().getName());
        }

        preFragmentTag = fragment.getClass().getName();
        transaction.commitAllowingStateLoss();
    }

    private boolean isDeleteFragment(String clazz) {
        boolean isDelete = false;
        for (String clazzName : deleteFragmentClazzList) {
            if (clazzName.equals(clazz)) {
                isDelete = true;
                break;
            }
        }
        return isDelete;
    }
}