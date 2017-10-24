package com.wenjiang.wenbiao.fategrandorder.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by wenbiao on 2017/10/24.
 */

public abstract class BaseFragment extends Fragment {

    protected abstract void onUserVisible();

    protected abstract void onUserInVisible();

    protected abstract void onFirstVisible();

    private View rootView;
    private boolean isFirstVisible = true;
    private boolean isFragmentVisible = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (rootView == null) {
            return;
        }

        if (isFirstVisible && isVisibleToUser) {
            onFirstVisible();
            isFirstVisible = false;
            return;
        }

        if (isVisibleToUser) {
            onUserVisible();
            isFragmentVisible = true;
            return;
        }

        if (isFragmentVisible) {
            onUserInVisible();
            isFragmentVisible = false;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = view;
            if (getUserVisibleHint()) {
                if (isFirstVisible) {
                    onFirstVisible();
                    isFirstVisible = false;
                }

                onUserVisible();
                isFragmentVisible = true;
            }
        }
        super.onViewCreated(rootView, savedInstanceState);
    }
}
