package com.wenjiang.wenbiao.fategrandorder.model;

import com.wenjiang.wenbiao.fategrandorder.database.database.BaseTable;
import com.wenjiang.wenbiao.fategrandorder.database.database.Column;
import com.wenjiang.wenbiao.fategrandorder.database.database.ColumnType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenbiao on 2017/11/20.
 */

public class FriendInfo extends BaseTable {
    @Column
    private long userId = 0;
    @Column
    private String headUrl = "";
    @Column
    private String phone = "";
    @Column
    private String name = "";
    @Column
    @ColumnType(ColumnType = "String")
    private List<Long> fateList = new ArrayList<>();
    @Column
    @ColumnType(ColumnType = "String")
    private List<Long> courtesyList = new ArrayList<>();

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getFateList() {
        return fateList;
    }

    public void setFateList(List<Long> fateList) {
        this.fateList = fateList;
    }

    public List<Long> getCourtesyList() {
        return courtesyList;
    }

    public void setCourtesyList(List<Long> courtesyList) {
        this.courtesyList = courtesyList;
    }

    @Override
    public String toString() {
        return "FriendInfo{" +
                "userId=" + userId +
                ", headUrl='" + headUrl + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", fateList=" + fateList +
                ", courtesyList=" + courtesyList +
                '}';
    }
}
