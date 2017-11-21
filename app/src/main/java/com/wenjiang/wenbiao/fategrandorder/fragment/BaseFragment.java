package com.wenjiang.wenbiao.fategrandorder.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.wenjiang.wenbiao.fategrandorder.activity.BaseActivity;
import com.wenjiang.wenbiao.fategrandorder.log.Logger;
import com.wenjiang.wenbiao.fategrandorder.skin.DynamicAttr;
import com.wenjiang.wenbiao.fategrandorder.skin.IDynamicNewView;

import java.util.List;

/**
 * Created by wenbiao on 2017/10/24.
 */

public abstract class BaseFragment extends Fragment implements IDynamicNewView {

    protected abstract void onUserVisible();

    protected abstract void onUserInVisible();

    protected abstract void onFirstVisible();

    public abstract Bundle saveState();

    public abstract void restoreState(Bundle bundle);

    private View rootView;
    private boolean isFirstVisible = true;
    private boolean isFragmentVisible = false;
    protected DataObserverListener listener;
    private IDynamicNewView iDynamicNewView;

    public void setDataListener(DataObserverListener listener){
        this.listener = listener;
    }

    public DataObserverListener getDataListener(){
        return listener;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Logger.e("tag", "setUserVisibleHint");
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
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            iDynamicNewView = (IDynamicNewView)context;
        }catch(ClassCastException e){
            iDynamicNewView = null;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(getActivity() instanceof BaseActivity){
            ((BaseActivity)getActivity()).getTaskManager().cancelAll();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            onUserInVisible();
        }else{
            onUserVisible();
        }
    }

    @Override
    public void dynamicAddView(View view, List<DynamicAttr> pDAttrs) {
        if(iDynamicNewView == null){
            throw new RuntimeException("IDynamicNewView should be implements !");
        }else{
            iDynamicNewView.dynamicAddView(view, pDAttrs);
        }
    }

    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        LayoutInflater result = getActivity().getLayoutInflater();
        return result;
    }

    public interface DataObserverListener<T>{
         void setData(T data);
         T getData();
    }
}
