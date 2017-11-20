package com.wenjiang.wenbiao.fategrandorder.model;

import com.wenjiang.wenbiao.fategrandorder.database.database.BaseTable;
import com.wenjiang.wenbiao.fategrandorder.database.database.Column;
import com.wenjiang.wenbiao.fategrandorder.database.database.ColumnType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenbiao on 2017/11/20.
 */

public class RecommendInfo extends BaseTable {
    @Column
    @ColumnType(ColumnType = "String")
    private List<Long> recommendFate = new ArrayList<>();
    @Column
    @ColumnType(ColumnType = "String")
    private List<Long> recommendCourtesy = new ArrayList<>();
    @Column
    @ColumnType(ColumnType = "String")
    private List<Long> recommendFriend = new ArrayList<>();
}
