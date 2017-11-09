package com.wenjiang.wenbiao.fategrandorder.async;

/**
 * Created by wenbiao on 2017/11/9.
 */

public interface FailCallback<K> {
    void onError(K attachment, Exception e);
}
