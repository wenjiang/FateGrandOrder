package com.wenjiang.wenbiao.fategrandorder.database.database;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by wenbiao on 2017/11/8.
 */

@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {
    String table() default "";
}
