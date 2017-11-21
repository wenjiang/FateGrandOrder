package com.wenjiang.wenbiao.fategrandorder.utils;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by wenbiao on 2017/10/23.
 */

public final class SettingUtil {
    private static final String TAG = "SettingUtil";

    public static boolean isAccessibilitySettingsOn(Context context) {
        int accessibilityEnabled = 0;
        // com.z.buildingaccessibilityservices/android.accessibilityservice.AccessibilityService
        try {
            accessibilityEnabled = Settings.Secure.getInt(context.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
            Log.v(TAG, "accessibilityEnabled = " + accessibilityEnabled);
        } catch (Settings.SettingNotFoundException e) {
            Log.e(TAG, "Error finding setting, default accessibility to not found: " + e.getMessage());
        }
        if (accessibilityEnabled == 1) {
            Log.v(TAG, "ACCESSIBILITY IS ENABLED");
            return true;
        } else {
            Log.v(TAG, "ACCESSIBILITY IS DISABLED");
            return false;
        }
    }
}
