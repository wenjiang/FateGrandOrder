package com.wenjiang.wenbiao.fategrandorder.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.wenjiang.wenbiao.fategrandorder.async.AsyncTaskManager;
import com.wenjiang.wenbiao.fategrandorder.skin.DynamicAttr;
import com.wenjiang.wenbiao.fategrandorder.skin.IDynamicNewView;
import com.wenjiang.wenbiao.fategrandorder.skin.ISkinUpdate;
import com.wenjiang.wenbiao.fategrandorder.skin.SkinInflaterFactory;
import com.wenjiang.wenbiao.fategrandorder.skin.SkinManager;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by wenbiao on 2017/11/7.
 */

public class BaseActivity extends AppCompatActivity implements ISkinUpdate, IDynamicNewView {
    private boolean isResponseOnSkinChanging = true;
    private SkinInflaterFactory mSkinInflaterFactory;
    protected AsyncTaskManager taskManager;

    @Override
    public void dynamicAddView(View view, List<DynamicAttr> pDAttrs) {
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
    }

    @Override
    public void onThemeUpdate() {
        if(!isResponseOnSkinChanging) return;
        mSkinInflaterFactory.applySkin();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Field field = LayoutInflater.class.getDeclaredField("mFactorySet");
            field.setAccessible(true);
            field.setBoolean(getLayoutInflater(), false);

            mSkinInflaterFactory = new SkinInflaterFactory();
            getLayoutInflater().setFactory(mSkinInflaterFactory);

            taskManager = new AsyncTaskManager(this);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().detach(this);
        taskManager.cancelAll();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SkinManager.getInstance().attach(this);
    }

    protected void dynamicAddSkinEnableView(View view, String attrName, int attrValueResId){
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, attrName, attrValueResId);
    }

    protected void dynamicAddSkinEnableView(View view, List<DynamicAttr> pDAttrs){
        mSkinInflaterFactory.dynamicAddSkinEnableView(this, view, pDAttrs);
    }

    final protected void enableResponseOnSkinChanging(boolean enable){
        isResponseOnSkinChanging = enable;
    }

    public AsyncTaskManager getTaskManager(){
        return taskManager;
    }
}
