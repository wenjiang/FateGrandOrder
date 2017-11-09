package com.wenjiang.wenbiao.fategrandorder.network;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by wenbiao on 2017/11/9.
 */

public class CustomCallback<T> implements CustomCallbackInterface<T> {
    private boolean isCancel = false;

    @Override
    public void success(T data, Response response) {
    }

    @Override
    public void failure(RetrofitError error) {

    }

    @Override
    public String getTag() {
        return null;
    }

    @Override
    public String setTag(String tag) {
        return null;
    }

    @Override
    public void cancel() {
        isCancel = true;
    }

    @Override
    public boolean isCanceled() {
        return isCancel;
    }
}
