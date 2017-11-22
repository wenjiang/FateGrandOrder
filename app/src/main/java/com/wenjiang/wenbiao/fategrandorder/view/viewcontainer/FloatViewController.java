package com.wenjiang.wenbiao.fategrandorder.view.viewcontainer;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.wenjiang.wenbiao.fategrandorder.constant.Constant;
import com.wenjiang.wenbiao.fategrandorder.view.FloatView;

/**
 * Created by wenbiao on 2017/11/21.
 */

public class FloatViewController extends BaseViewController implements FloatView.OnFloatViewClickListener{

    private FloatView floatView;
    private AccessibilityReceiver receiver;
    private IntentFilter intentFilter;

    private FloatViewController(Activity activity){
        init(activity);
    }

    public static FloatViewController newInstance(Activity activity){
        return new FloatViewController(activity);
    }

    @Override
    protected void init(Activity activity) {
        floatView = new FloatView(activity);
        floatView.setOnFloatViewClickListener(this);
        receiver = new AccessibilityReceiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.BroadcastAction.ACCESSIBILITY_BROADCAST_ACTION);
    }

    @Override
    public void onFloatViewClick() {

    }

    private void showView() {
        floatView.showView();
    }

    private void dismissView() {
        floatView.removeView();
    }

    public void unregisterReceiver(Context context){
        LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
    }

    public void registerReceiver(Context context){
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, intentFilter);
    }

    public class AccessibilityReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            boolean isFgoOpen = intent.getBooleanExtra(Constant.IntentKey.INTENT_KEY_ACCESSIBILITY, false);
            if (isFgoOpen) {
                //收到FGO应用被打开的消息
                showView();
            } else {
                //收到FGO应用退到后台的消息
                dismissView();
            }
        }
    }
}
