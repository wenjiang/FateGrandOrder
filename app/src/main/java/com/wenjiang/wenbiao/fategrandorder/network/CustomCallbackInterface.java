package com.wenjiang.wenbiao.fategrandorder.network;

import retrofit.Callback;

/**
 * Created by wenbiao on 2017/11/9.
 */

public interface CustomCallbackInterface<T> extends Callback<T> {
    String getTag();

    String setTag(String tag);

    void cancel();

    boolean isCanceled();
}
