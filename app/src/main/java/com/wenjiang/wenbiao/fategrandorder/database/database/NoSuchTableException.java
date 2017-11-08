package com.wenjiang.wenbiao.fategrandorder.database.database;

/**
 * Created by wenbiao on 2017/11/8.
 */

public class NoSuchTableException extends BaseSQLiteException {
    public NoSuchTableException(String message) {
        super(message);
    }
}
