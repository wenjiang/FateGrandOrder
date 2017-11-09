package com.wenjiang.wenbiao.fategrandorder.async;

/**
 * Created by wenbiao on 2017/11/9.
 */

public interface DoneCallback<K, T> {
    void onSuccess(K attachment, T result);
}
