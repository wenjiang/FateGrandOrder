package com.wenjiang.wenbiao.fategrandorder.view.viewcontainer;

import android.app.Activity;

import com.wenjiang.wenbiao.fategrandorder.R;
import com.wenjiang.wenbiao.fategrandorder.test.TestBottomData;
import com.wenjiang.wenbiao.fategrandorder.view.BottomMenuView;

/**
 * Created by wenbiao on 2017/11/21.
 */

public final class BottomViewController extends BaseViewController implements BottomMenuView.OnBottomClick {

    private MainFragmentViewController mainFragmentViewController;
    private TestBottomData bottomData;

    private BottomViewController(Activity activity) {
        init(activity);
    }

    public static BottomViewController newInstance(Activity activity) {
        return new BottomViewController(activity);
    }

    @Override
    protected void init(Activity activity) {
        mainFragmentViewController = MainFragmentViewController.newInstance(activity);
        BottomMenuView bottomMenuView = activity.findViewById(R.id.view_bottom);
        bottomData = TestBottomData.getInstance(activity);
        bottomMenuView.init(bottomData.getTextSize(), bottomData.getTextColor(), bottomData.getDivideColor(), bottomData.getText());
        bottomMenuView.setOnBottomClick(this);
    }

    @Override
    public void onClick(String type) {
         int index = bottomData.getText().indexOf(type);
         String tag = bottomData.getType().get(index);
         mainFragmentViewController.showFragment(tag);
    }
}
