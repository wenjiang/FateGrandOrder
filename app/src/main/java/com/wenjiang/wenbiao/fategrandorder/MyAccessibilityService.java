package com.wenjiang.wenbiao.fategrandorder;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by wenbiao on 2017/10/20.
 */

public class MyAccessibilityService extends AccessibilityService {
    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Log.e("tag", accessibilityEvent.getPackageName().toString());
    }

    @Override
    public void onInterrupt() {

    }
}
