package com.wenjiang.wenbiao.fategrandorder.log;

/**
 * Created by wenbiao on 2017/10/30.
 */

public interface LogTool {
    void d(String tag, String message);

    void e(String tag, String message);

    void w(String tag, String message);

    void i(String tag, String message);

    void v(String tag, String message);

    void wtf(String tag, String message);
}