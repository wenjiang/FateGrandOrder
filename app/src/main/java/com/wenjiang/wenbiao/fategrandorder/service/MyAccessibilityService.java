package com.wenjiang.wenbiao.fategrandorder.service;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.accessibility.AccessibilityEvent;

import com.wenjiang.wenbiao.fategrandorder.constant.Constant;

/**
 * Created by wenbiao on 2017/10/20.
 */

public class MyAccessibilityService extends AccessibilityService {
    private Intent intent = new Intent(Constant.BroadcastAction.ACCESSIBILITY_BROADCAST_ACTION);
    private final String FGO_PACKAGE = "com.lifesense.LSWearable.intl.qa";
    private String prePackageName = "";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getPackageName().toString().contains(FGO_PACKAGE)) {
            prePackageName = FGO_PACKAGE;
            intent.putExtra(Constant.IntentKey.INTENT_KEY_ACCESSIBILITY, true);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        } else if (isFgoBackground(accessibilityEvent)) {
            prePackageName = "";
            intent.putExtra(Constant.IntentKey.INTENT_KEY_ACCESSIBILITY, false);
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }
    }

    @Override
    public void onInterrupt() {

    }

    /**
     * 判断FGO是否退到后台
     *
     * @param accessibilityEvent
     * @return
     */
    private boolean isFgoBackground(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED && prePackageName.contains(FGO_PACKAGE) && !accessibilityEvent.getPackageName().toString().contains(FGO_PACKAGE) && !accessibilityEvent.getPackageName().toString().equals(getPackageName())) {
            return true;
        } else {
            return false;
        }
    }
}
