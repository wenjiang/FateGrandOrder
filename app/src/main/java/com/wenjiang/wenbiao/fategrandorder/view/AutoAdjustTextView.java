package com.wenjiang.wenbiao.fategrandorder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.wenjiang.wenbiao.fategrandorder.R;
import com.wenjiang.wenbiao.fategrandorder.log.Logger;
import com.wenjiang.wenbiao.fategrandorder.utils.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenbiao on 2017/11/23.
 */

public class AutoAdjustTextView extends AppCompatTextView {

    private float textSize = 0;

    public AutoAdjustTextView(Context context) {
        super(context);
    }

    public AutoAdjustTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoAdjustTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int lineCount = getLayout().getLineCount();
        if (lineCount > 1) {
            textSize = ViewUtil.px2sp(getContext(), getTextSize()) - lineCount;
            AutoTextSizeManager.getInstance().setSize(textSize);
        }
    }
}
