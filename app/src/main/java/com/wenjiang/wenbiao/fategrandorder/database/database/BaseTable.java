package com.wenjiang.wenbiao.fategrandorder.database.database;

import android.content.ContentValues;

import com.wenjiang.wenbiao.fategrandorder.log.Logger;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wenbiao on 2017/11/8.
 */

public class BaseTable implements Serializable {
    /**
     * 删除操作
     *
     * @throws NoSuchTableException
     */
    public void delete() throws Exception {
        Map<String, Object> valueMap = new HashMap<>();
        String tableName = "";
        Field[] fields = this.getClass().getDeclaredFields();
        if (this.getClass().isAnnotationPresent(Table.class)) {
            Table table = this.getClass().getAnnotation(Table.class);
            tableName = table.table();
            if (tableName.length() == 0) {
                throw new NoSuchTableException("The table " + getClass().getSimpleName() + " is not exist");
            }
        }

        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column meta = field.getAnnotation(Column.class);
                String column = meta.column();
                if (column.equals("")) {
                    column = field.getName();
                }
                field.setAccessible(true);
                Object value = null;
                try {
                    value = field.get(this);
                } catch (IllegalAccessException e) {
                    Logger.e(e.toString());
                }
                valueMap.put(column, value);
            }
        }
        DatabaseStore.getInstance().delete(tableName, valueMap);
    }

    /**
     * 保存操作
     *
     * @throws NoSuchTableException
     */
    public void save() throws Exception {
        String tableName = "";
        Field[] fields = this.getClass().getDeclaredFields();
        if (this.getClass().isAnnotationPresent(Table.class)) {
            Table table = this.getClass().getAnnotation(Table.class);
            tableName = table.table();
            if (tableName.length() == 0) {
                throw new NoSuchTableException("The table " + getClass().getSimpleName() + " is not exist");
            }
        }else{
            tableName = this.getClass().getSimpleName();
        }

        ContentValues values = new ContentValues();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column meta = field.getAnnotation(Column.class);
                String column = meta.column();
                if (column.equals("")) {
                    column = field.getName();
                }

                String type = "";
                if (field.isAnnotationPresent(ColumnType.class)) {
                    ColumnType fieldType = field.getAnnotation(ColumnType.class);
                    type = fieldType.ColumnType();
                } else {
                    type = field.getType().getName();
                }
                field.setAccessible(true);
                if (!type.equals("")) {
                    Object value = null;
                    try {
                        value = field.get(this);
                    } catch (IllegalAccessException e) {
                        Logger.e(e.toString());
                    }
                    if (type.contains("String")) {
                        if (value != null) {
                            values.put(column, value.toString());
                        }else{
                            values.put(column, "");
                        }
                    } else if (type.equals("int")) {
                        values.put(column, (int) value);
                    } else if (type.equals("double")) {
                        values.put(column, (double) value);
                    } else if (type.equals("float")) {
                        values.put(column, (float) value);
                    } else if (type.equals("boolean")) {
                        values.put(column, (boolean) value);
                    } else if (type.equals("long")) {
                        values.put(column, (long) value);
                    } else if (type.equals("short")) {
                        values.put(column, (short) value);
                    }
                }
            }
        }
        DatabaseStore.getInstance().save(tableName, values);
    }
}
