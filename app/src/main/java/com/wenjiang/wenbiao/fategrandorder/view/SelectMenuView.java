package com.example.weber_zheng.myapplication;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by weber_zheng on 2017/12/4.
 */

public class SelectMenuView extends LinearLayout {

    private LinearLayout rootView;

    public SelectMenuView(Context context) {
        this(context, null);
    }

    public SelectMenuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public SelectMenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SelectMenuView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void initView() {
        this.rootView = (LinearLayout) inflate(getContext(), R.layout.view_select_menu, this).findViewById(R.id.ll_root);
    }

    public void init(int count, List<String> tabList, int drawable, int radius, int rightMargin, int dividerColor) {
        for (int i = 0; i < count; i++) {
            DrawableTextView textView = new DrawableTextView(getContext());
            textView.setText(tabList.get(i));
            rootView.addView(textView);
            textView.setRightDrawable(drawable, radius, rightMargin);
            textView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.weight = 1;
            layoutParams.topMargin = ViewUtil.dip2px(getContext(), 15);
            textView.setLayoutParams(layoutParams);

            if(i != count - 1) {
                View divider = new View(getContext());
                divider.setBackgroundResource(dividerColor);
                rootView.addView(divider);
                LinearLayout.LayoutParams dividerLayoutParams = (LinearLayout.LayoutParams) divider.getLayoutParams();
                dividerLayoutParams.width = ViewUtil.dip2px(getContext(), 1);
                dividerLayoutParams.height = layoutParams.MATCH_PARENT;
                dividerLayoutParams.topMargin = ViewUtil.dip2px(getContext(), 10);
                divider.setLayoutParams(dividerLayoutParams);
            }
        }
    }
}
