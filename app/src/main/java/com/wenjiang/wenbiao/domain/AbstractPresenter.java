package com.wenjiang.wenbiao.domain;

/**
 * Created by wenbiao on 2017/10/16.
 */

public abstract class AbstractPresenter {
    protected Executor mExecutor;
    protected MainThread mMainThread;

    public AbstractPresenter(Executor executor, MainThread mainThread) {
        mExecutor = executor;
        mMainThread = mainThread;
    }
}
