package com.wenjiang.wenbiao.fategrandorder.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wenjiang.wenbiao.fategrandorder.utils.ViewUtil;

/**
 * Created by wenbiao on 2017/11/21.
 */

@SuppressLint("AppCompatCustomView")
public class DrawableTextView extends TextView {
    public DrawableTextView(Context context) {
        super(context);
    }

    public DrawableTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setLeftDrawable(int drawableId, int radius, int leftMargin) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if(layoutParams instanceof LinearLayout.LayoutParams){
            ((LinearLayout.LayoutParams)layoutParams).leftMargin = ViewUtil.dip2px(getContext(), leftMargin);
        }else if(layoutParams instanceof RelativeLayout.LayoutParams){
            ((RelativeLayout.LayoutParams)layoutParams).leftMargin = ViewUtil.dip2px(getContext(), leftMargin);
        }

        setLayoutParams(layoutParams);
        Drawable drawable = getResources().getDrawable(drawableId);
        drawable.setBounds(0, 0, ViewUtil.dip2px(getContext(), radius), ViewUtil.dip2px(getContext(), radius));
        setCompoundDrawables(drawable, null, null, null);
    }
}
