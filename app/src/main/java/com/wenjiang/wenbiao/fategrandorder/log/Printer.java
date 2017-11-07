package com.wenjiang.wenbiao.fategrandorder.log;

import java.util.List;

/**
 * Created by wenbiao on 2017/10/30.
 */

public interface Printer {

    Printer t(String tag, int methodCount);

    Settings init(String tag);

    Settings getSettings();

    void d(String message, Object... args);

    void e(String message, Object... args);

    void e(Throwable throwable, String message, Object... args);

    void w(String message, Object... args);

    void i(String message, Object... args);

    void v(String message, Object... args);

    void wtf(String message, Object... args);

    void json(String json);

    void xml(String xml);

    <T> void list(List<T> list);

    void clear();
}