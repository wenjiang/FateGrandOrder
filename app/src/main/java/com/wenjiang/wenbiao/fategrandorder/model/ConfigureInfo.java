package com.wenjiang.wenbiao.fategrandorder.model;

import com.wenjiang.wenbiao.fategrandorder.database.database.BaseTable;
import com.wenjiang.wenbiao.fategrandorder.database.database.Column;

/**
 * Created by wenbiao on 2017/11/20.
 */
public class ConfigureInfo extends BaseTable {
    @Column
    private long startTime = 0;
    @Column
    private String title = "";
    @Column
    private String icon = "";
    @Column
    private long endTime = 0;
    @Column
    private long configureId = 0;

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

    public long getConfigureId() {
        return configureId;
    }

    public void setConfigureId(long configureId) {
        this.configureId = configureId;
    }

    @Override
    public String toString() {
        return "ConfigureInfo{" +
                "startTime=" + startTime +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", endTime=" + endTime +
                ", configureId=" + configureId +
                '}';
    }
}
