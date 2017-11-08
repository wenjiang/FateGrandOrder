package com.wenjiang.wenbiao.fategrandorder.skin;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenbiao on 2017/11/7.
 */

public final class IconManager {
    private PackageManager packageManager;
    private static IconManager iconManager;
    private List<IconInfo> iconInfos;

    private IconManager(Activity activity) {
        iconInfos = new ArrayList<>();
        packageManager = activity.getPackageManager();
        iconInfos.add(new IconInfo("default", activity.getComponentName()));
        iconInfos.add(new IconInfo("test", new ComponentName(activity.getBaseContext(), "com.wenjiang.wenbiao.fategrandorder.Test")));
    }

    public static IconManager getInstance(Activity activity) {
        if (iconManager == null) {
            iconManager = new IconManager(activity);
        }

        return iconManager;
    }

    public void enableComponent(String componentName) throws Exception {
        if(!iconInfos.contains(componentName)){
            throw new Exception("Please enter right component");
        }
        for (IconInfo iconInfo : iconInfos) {
            if (iconInfo.name.equals(componentName)) {
                packageManager.setComponentEnabledSetting(iconInfo.componentName,
                        PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                        PackageManager.DONT_KILL_APP);
            } else {
                packageManager.setComponentEnabledSetting(iconInfo.componentName,
                        PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
            }
        }
    }

    private static class IconInfo {
        String name;
        ComponentName componentName;

        public IconInfo(String name, ComponentName componentName) {
            this.name = name;
            this.componentName = componentName;
        }
    }
}
