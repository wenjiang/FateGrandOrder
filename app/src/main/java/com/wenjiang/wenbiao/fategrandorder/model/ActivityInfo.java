package com.wenjiang.wenbiao.fategrandorder.model;

import com.wenjiang.wenbiao.fategrandorder.database.database.BaseTable;
import com.wenjiang.wenbiao.fategrandorder.database.database.Column;
import com.wenjiang.wenbiao.fategrandorder.database.database.ColumnType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenbiao on 2017/11/20.
 */

public class ActivityInfo extends BaseTable {
    @Column
    private long startTime = 0;
    @Column
    private String title = "";
    @Column
    private String icon = "";
    @Column
    private long endTime = 0;
    @Column
    private long activityId = 0;
    @Column
    private String url = "";
    @Column
    @ColumnType(ColumnType = "String")
    private List<Long> recommendFate = new ArrayList<>();
    @Column
    @ColumnType(ColumnType = "String")
    private List<Long> recommendCourtesy = new ArrayList<>();

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Long> getRecommendFate() {
        return recommendFate;
    }

    public void setRecommendFate(List<Long> recommendFate) {
        this.recommendFate = recommendFate;
    }

    public List<Long> getRecommendCourtesy() {
        return recommendCourtesy;
    }

    public void setRecommendCourtesy(List<Long> recommendCourtesy) {
        this.recommendCourtesy = recommendCourtesy;
    }

    @Override
    public String toString() {
        return "ActivityInfo{" +
                "startTime=" + startTime +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", endTime=" + endTime +
                ", activityId=" + activityId +
                ", url='" + url + '\'' +
                ", recommendFate=" + recommendFate +
                ", recommendCourtesy=" + recommendCourtesy +
                '}';
    }
}
