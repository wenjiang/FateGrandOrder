package com.wenjiang.wenbiao.fategrandorder.database.database;

/**
 * Created by wenbiao on 2017/11/8.
 */

public class NoSuchColumnException extends BaseSQLiteException {
    public NoSuchColumnException(String message) {
        super(message);
    }
}
