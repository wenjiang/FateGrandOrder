package com.wenjiang.wenbiao.domain;

/**
 * Created by wenbiao on 2017/10/16.
 */

public class HelloWorldInteractorImpl extends AbstractInteractor implements HelloWorldInteractor {
    private HelloWorldInteractor.Callback mCallback;

    public HelloWorldInteractorImpl(Executor threadExecutor,
                                    MainThread mainThread,
                                    Callback callback) {
        super(threadExecutor, mainThread);
        mCallback = callback;
    }

    @Override
    public void run() {
        if(mCallback != null){
            mCallback.showHello();
        }
    }
}
