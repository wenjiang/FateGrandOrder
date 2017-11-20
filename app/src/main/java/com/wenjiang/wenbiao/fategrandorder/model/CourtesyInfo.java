package com.wenjiang.wenbiao.fategrandorder.model;

import com.wenjiang.wenbiao.fategrandorder.database.database.BaseTable;
import com.wenjiang.wenbiao.fategrandorder.database.database.Column;

/**
 * Created by wenbiao on 2017/11/20.
 */

public class CourtesyInfo extends BaseTable {
    @Column
    private long courtesyId = 0;
    @Column
    private String name = "";
    @Column
    private String icon = "";
    @Column
    private String url = "";
    @Column
    private String feature = "";
    @Column
    private String description = "";
    @Column
    private int star = 0;

    public long getCourtesyId() {
        return courtesyId;
    }

    public void setCourtesyId(long courtesyId) {
        this.courtesyId = courtesyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    @Override
    public String toString() {
        return "CourtesyInfo{" +
                "courtesyId=" + courtesyId +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", url='" + url + '\'' +
                ", feature='" + feature + '\'' +
                ", description='" + description + '\'' +
                ", star=" + star +
                '}';
    }
}
