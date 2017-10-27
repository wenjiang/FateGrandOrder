package com.wenjiang.wenbiao.fategrandorder.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

/**
 * Created by wenbiao on 2017/10/24.
 */

public abstract class BaseFragment extends Fragment {

    protected abstract void onUserVisible();

    protected abstract void onUserInVisible();

    protected abstract void onFirstVisible();

    public abstract Bundle saveState();

    public abstract void restoreState(Bundle bundle);

    private View rootView;
    private boolean isFirstVisible = true;
    private boolean isFragmentVisible = false;
    protected DataObserverListener listener;

    public void setDataListener(DataObserverListener listener){
        this.listener = listener;
    }

    public DataObserverListener getDataListener(){
        return listener;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.e("tag", "setUserVisibleHint");
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e("tag", "onViewCreated");
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        Log.e("tag", hidden + "");
//        if(hidden){
//            onUserInVisible();
//        }else{
//            onUserVisible();
//        }
    }

    public interface DataObserverListener<T>{
         void setData(T data);
         T getData();
    }
}
