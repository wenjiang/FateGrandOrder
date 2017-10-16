package com.wenjiang.wenbiao.domain;

/**
 * Created by wenbiao on 2017/10/16.
 */

public interface MainThread {
    void post(final Runnable runnable);
}
