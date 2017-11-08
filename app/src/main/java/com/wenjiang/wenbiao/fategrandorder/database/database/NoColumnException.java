package com.wenjiang.wenbiao.fategrandorder.database.database;

/**
 * Created by wenbiao on 2017/11/8.
 */

public class NoColumnException extends BaseSQLiteException {
    public NoColumnException(String message) {
        super(message);
    }
}
