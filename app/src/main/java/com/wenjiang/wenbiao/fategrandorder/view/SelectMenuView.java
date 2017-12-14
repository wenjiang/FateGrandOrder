package com.wenjiang.wenbiao.fategrandorder.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.wenjiang.wenbiao.fategrandorder.R;
import com.wenjiang.wenbiao.fategrandorder.test.TestTopData;
import com.wenjiang.wenbiao.fategrandorder.utils.ViewUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by weber_zheng on 2017/12/4.
 */

public class SelectMenuView extends LinearLayout {
    private Map<Integer, List<String>> tabMap;
    private LinearLayout llMenu;
    private LinearLayout llTab;

    public SelectMenuView(Context context) {
        this(context, null);
    }

    public SelectMenuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        tabMap = new HashMap<>();
        initView();
    }

    public SelectMenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initView() {
        View view = inflate(getContext(), R.layout.view_select_menu, this);
        this.llTab = (LinearLayout) view.findViewById(R.id.ll_top);
        this.llMenu = (LinearLayout) view.findViewById(R.id.ll_tab);
    }

    public void init(final int count, List<String> tabList, int drawable, int radius, int rightMargin, final int dividerColor) {
        TestTopData testTopData = TestTopData.getInstance(getContext());
        int grandCount = testTopData.getGrandCount();
        boolean hasGrand = false;
        if (grandCount > 0) {
            hasGrand = true;
        }
        final boolean isShowGrand = hasGrand;
        for (int i = 0; i < count; i++) {
            final int index = i;
            List<String> subList = testTopData.getSubText(i);
            tabMap.put(i, subList);
            final DrawableTextView textView = new DrawableTextView(getContext());
            textView.setText(tabList.get(i));
            llTab.addView(textView);
            textView.setRightDrawable(drawable, radius, rightMargin);
            textView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.weight = 1;
            layoutParams.topMargin = ViewUtil.dip2px(getContext(), 15);
            textView.setLayoutParams(layoutParams);
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    llMenu.removeAllViews();
                    showTab(index, dividerColor, textView, count, isShowGrand);
                }
            });

            if (i != count - 1) {
                View divider = new View(getContext());
                divider.setBackgroundResource(dividerColor);
                llTab.addView(divider);
                LinearLayout.LayoutParams dividerLayoutParams = (LinearLayout.LayoutParams) divider.getLayoutParams();
                dividerLayoutParams.width = ViewUtil.dip2px(getContext(), 1);
                dividerLayoutParams.height = ViewUtil.dip2px(getContext(), 20);
                dividerLayoutParams.gravity = Gravity.CENTER_VERTICAL;
                dividerLayoutParams.topMargin = ViewUtil.dip2px(getContext(), 5);
                divider.setLayoutParams(dividerLayoutParams);
            }
        }
    }

    private void showTab(int index, int dividerColor, final DrawableTextView tabTextView, int count, boolean isShowGrand) {
        List<String> subTextList = tabMap.get(index);
        int grandIndex = 0;
        for (int i = 0, size = subTextList.size(); i < size; i++) {
            final DrawableTextView textView = new DrawableTextView(getContext());
            textView.setGravity(Gravity.CENTER);
            if (tabTextView.getText().toString().equals(subTextList.get(i))) {
                textView.setTextColor(getResources().getColor(R.color.colorPrimary));
            }
            textView.setText(subTextList.get(i));
            llMenu.addView(textView);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
            layoutParams.topMargin = ViewUtil.dip2px(getContext(), 5);
            textView.setLayoutParams(layoutParams);
            View divider = new View(getContext());
            divider.setBackgroundResource(dividerColor);
            llMenu.addView(divider);
            LinearLayout.LayoutParams dividerLayoutParams = (LinearLayout.LayoutParams) divider.getLayoutParams();
            dividerLayoutParams.height = ViewUtil.dip2px(getContext(), 0.5f);
            dividerLayoutParams.topMargin = ViewUtil.dip2px(getContext(), 5);
            divider.setLayoutParams(dividerLayoutParams);
            if (isShowGrand && index == count - 1) {
                final LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                String grandText = TestTopData.getInstance(getContext()).getGrandText().get(grandIndex);
                for (String grand : grandText.split(",")) {
                    DrawableTextView tvGrand = new DrawableTextView(getContext());
                    tvGrand.setText(grand);
                    linearLayout.addView(tvGrand);
                }
                textView.setRightDrawable(R.mipmap.ic_keyboard_arrow_down_black, 15, 10);
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(linearLayout.getVisibility() == View.GONE){
                            linearLayout.setVisibility(View.VISIBLE);
                        }else{
                            linearLayout.setVisibility(View.GONE);
                        }
                    }
                });
                linearLayout.setVisibility(View.GONE);
                llMenu.addView(linearLayout);
                grandIndex++;
            } else {
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tabTextView.setText(textView.getText().toString());
                        llMenu.removeAllViews();
                    }
                });
            }
        }
    }
}
