package com.wenjiang.wenbiao.fategrandorder.view.viewcontainer;

import android.app.Activity;
import android.view.View;

import com.wenjiang.wenbiao.fategrandorder.R;
import com.wenjiang.wenbiao.fategrandorder.log.Logger;
import com.wenjiang.wenbiao.fategrandorder.view.DrawableTextView;

/**
 * Created by wenbiao on 2017/11/21.
 */

public final class BottomViewController extends BaseViewController implements View.OnClickListener {

    private DrawableTextView tvLeft;
    private DrawableTextView tvCenter;
    private DrawableTextView tvRight;
    private TextClickListener listener;

    private BottomViewController(Activity activity) {
        init(activity);
    }

    public static BottomViewController newInstance(Activity activity) {
        return new BottomViewController(activity);
    }

    @Override
    protected void init(Activity activity) {
        tvLeft = (DrawableTextView) activity.findViewById(R.id.tv_bottom_left);
        tvLeft.setText(R.string.bottom_left);
        tvLeft.setOnClickListener(this);
        tvCenter = (DrawableTextView) activity.findViewById(R.id.tv_bottom_center);
        tvCenter.setText(R.string.bottom_center);
        tvCenter.setOnClickListener(this);
        tvRight = (DrawableTextView) activity.findViewById(R.id.tv_bottom_right);
        tvRight.setText(R.string.bottom_right);
        tvRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (listener == null) {
            return;
        }

        switch (view.getId()) {
            case R.id.tv_bottom_left:
                listener.onLeftClick();
                break;
            case R.id.tv_bottom_center:
                listener.onCenterClick();
                break;
            case R.id.tv_bottom_right:
                listener.onRightClick();
                break;
            default:
                break;
        }
    }

    public void setOnTextClickListener(TextClickListener listener) {
        this.listener = listener;
    }

    public interface TextClickListener {
        void onLeftClick();

        void onCenterClick();

        void onRightClick();
    }
}
