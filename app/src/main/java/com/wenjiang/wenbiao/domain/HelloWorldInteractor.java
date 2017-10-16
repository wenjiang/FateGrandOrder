package com.wenjiang.wenbiao.domain;

/**
 * Created by wenbiao on 2017/10/16.
 */

public interface HelloWorldInteractor extends Interactor {
    interface Callback {
        void showHello();
    }
}
