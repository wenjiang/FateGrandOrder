package com.wenjiang.wenbiao.fategrandorder.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wenjiang.wenbiao.fategrandorder.log.Logger;
import com.wenjiang.wenbiao.fategrandorder.utils.ViewUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by wenbiao on 2017/11/24.
 */

public class AutoAdjustLinearLayout extends LinearLayout {
    public AutoAdjustLinearLayout(Context context) {
        super(context);
    }

    public AutoAdjustLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoAdjustLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AutoAdjustLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int childCount = getChildCount();
        int totalWidth = 0;
        int totalTextLength = 0;
        Map<Integer, Integer> textSizeMap = new HashMap<>();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            int childViewWidth = childView.getMeasuredWidth();
            totalWidth += childViewWidth;
            if (childView instanceof TextView) {
                TextView textChildView = (TextView) childView;
                totalTextLength += textChildView.getText().length();
                textSizeMap.put(i, textChildView.getText().length());
            }
        }

        int textLengthFactor = totalWidth / totalTextLength;
        Set keySet = textSizeMap.keySet();
        Iterator iterator = keySet.iterator();
        while (iterator.hasNext()) {
            int index = (int) iterator.next();
            Logger.e("index:" + index + ", " + textLengthFactor * textSizeMap.get(index) + "");
            ((TextView) getChildAt(index)).setWidth(textLengthFactor * textSizeMap.get(index));
        }
    }
}
