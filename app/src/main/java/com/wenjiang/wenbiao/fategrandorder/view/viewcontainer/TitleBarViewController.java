package com.wenjiang.wenbiao.fategrandorder.view.viewcontainer;

import android.app.Activity;

import com.wenjiang.wenbiao.fategrandorder.R;
import com.wenjiang.wenbiao.fategrandorder.view.DrawableTextView;

/**
 * Created by wenbiao on 2017/11/21.
 */

public final class TitleBarViewController extends BaseViewController {

    private DrawableTextView tvLeft;
    private DrawableTextView tvCenter;
    private DrawableTextView tvRight;

    private TitleBarViewController(Activity activity) {
        init(activity);
    }

    public static TitleBarViewController newInstance(Activity activity) {
        return new TitleBarViewController(activity);
    }

    @Override
    protected void init(Activity activity) {
        tvLeft = (DrawableTextView) activity.findViewById(R.id.tv_title_left);
        tvCenter = (DrawableTextView) activity.findViewById(R.id.tv_title_center);
        tvRight = (DrawableTextView) activity.findViewById(R.id.tv_title_right);

        tvLeft.setLeftDrawable(R.mipmap.ic_navigate_before_black, 30, 0);
        tvLeft.setText(R.string.title_left);
    }
}
