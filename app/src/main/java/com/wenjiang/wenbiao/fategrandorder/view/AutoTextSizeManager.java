package com.wenjiang.wenbiao.fategrandorder.view;

import android.view.View;
import android.view.ViewGroup;

import com.wenjiang.wenbiao.fategrandorder.log.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenbiao on 2017/11/23.
 */

public class AutoTextSizeManager {
    private float size = 0;
    private static AutoTextSizeManager manager;
    private List<AutoAdjustTextView> autoAdjustTextViewList;

    private AutoTextSizeManager() {
        autoAdjustTextViewList = new ArrayList<>();
    }

    public static AutoTextSizeManager getInstance() {
        if (manager == null) {
            manager = new AutoTextSizeManager();
        }

        return manager;
    }

    public void setViewGroup(ViewGroup viewGroup) {
        int childViewCount = viewGroup.getChildCount();
        for (int i = 0; i < childViewCount; i++) {
            View childView = viewGroup.getChildAt(i);
            if (childView instanceof AutoAdjustTextView) {
                autoAdjustTextViewList.add((AutoAdjustTextView) childView);
            }
        }
    }

    public void setSize(float currentSize) {
        if (size == 0 || size > currentSize) {
            this.size = currentSize;
        }

        for(AutoAdjustTextView autoAdjustTextView : autoAdjustTextViewList){
            autoAdjustTextView.setTextSize(size);
        }
    }

    public float getSize(){
        return size;
    }
}
