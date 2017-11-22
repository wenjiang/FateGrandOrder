package com.wenjiang.wenbiao.fategrandorder.view;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wenjiang.wenbiao.fategrandorder.utils.ViewUtil;

import java.util.List;

/**
 * Created by wenbiao on 2017/11/22.
 */

public class BottomMenuView extends LinearLayout implements View.OnClickListener {
    private OnBottomClick onBottomClick;

    public BottomMenuView(Context context) {
        super(context);
    }

    public BottomMenuView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomMenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.bottomMenuView);
//        typedArray.recycle();
    }

    public void init(int itemTextSize, String textColorStr, String divideColorStr, List<String> textArr) {
        setOrientation(LinearLayout.HORIZONTAL);
        int textColor = Color.parseColor(textColorStr);
        int divideColor = Color.parseColor(divideColorStr);
        int itemCount = textArr.size();
        for (int i = 0; i < itemCount; i++) {
            TextView textView = new TextView(getContext());
            textView.setText(textArr.get(i));
            textView.setTextColor(textColor);
            textView.setTextSize(itemTextSize);
            textView.setGravity(Gravity.CENTER_HORIZONTAL);
            textView.setOnClickListener(this);
            addView(textView);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.height = LayoutParams.WRAP_CONTENT;
            layoutParams.width = LayoutParams.MATCH_PARENT;
            layoutParams.weight = 1;
            layoutParams.gravity = Gravity.CENTER_VERTICAL;
            textView.setLayoutParams(layoutParams);
            if (i != itemCount - 1) {
                View divideView = new View(getContext());
                divideView.setBackgroundColor(divideColor);
                addView(divideView);
                LinearLayout.LayoutParams divideLayoutParams = (LinearLayout.LayoutParams) divideView.getLayoutParams();
                divideLayoutParams.width = ViewUtil.dip2px(getContext(), 1);
                divideLayoutParams.height = LayoutParams.MATCH_PARENT;
                divideLayoutParams.bottomMargin = ViewUtil.dip2px(getContext(), 10);
                divideLayoutParams.topMargin = ViewUtil.dip2px(getContext(), 10);
                divideView.setLayoutParams(divideLayoutParams);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BottomMenuView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void onClick(View view) {
        if (view instanceof TextView) {
            String text = ((TextView) view).getText().toString();
            if (onBottomClick != null) {
                onBottomClick.onClick(text);
            }
        }
    }

    public void setOnBottomClick(OnBottomClick onBottomClick) {
        this.onBottomClick = onBottomClick;
    }

    public interface OnBottomClick {
        void onClick(String type);
    }
}
