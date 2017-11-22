package com.wenjiang.wenbiao.fategrandorder.model;

import com.google.gson.annotations.Expose;
import com.wenjiang.wenbiao.fategrandorder.database.database.BaseTable;
import com.wenjiang.wenbiao.fategrandorder.database.database.Column;

/**
 * Created by wenbiao on 2017/11/20.
 */

public class FateInfo extends BaseTable {
    @Column
    @Expose
    private String name = "";
    @Column
    @Expose
    private String jpName = "";
    @Column
    @Expose
    private String enName = "";
    @Column
    @Expose
    private String type = "";
    @Column
    @Expose
    private int originAtk = 0;
    @Column
    @Expose
    private int originHp = 0;
    @Column
    @Expose
    private int artsHits = 0;
    @Column
    @Expose
    private int busterHits = 0;
    @Column
    @Expose
    private int quickHits = 0;
    @Column
    @Expose
    private int extraHits = 0;
    @Column
    private String author = "";
    @Column
    @Expose
    private String cv = "";
    @Column
    @Expose
    private String attribute = "";
    @Column
    @Expose
    private String sex = "";
    @Column
    @Expose
    private double height = 0;
    @Column
    @Expose
    private double weight = 0;
    @Column
    @Expose
    private double starCount = 0;
    @Column
    @Expose
    private double dieReate = 0;
    @Column
    @Expose
    private double critWeight = 0;
    @Column
    @Expose
    private double npGetQ = 0;
    @Column
    @Expose
    private double npGetA = 0;
    @Column
    @Expose
    private double npGetB = 0;
    @Column
    @Expose
    private double npGetEx = 0;
    @Column
    @Expose
    private double npGetTreasure = 0;
    @Column
    @Expose
    private double npGetInjred = 0;
    @Column
    @Expose
    private String growth = "";
    @Column
    @Expose
    private String from = "";
    @Column
    @Expose
    private String feature = "";
    @Column
    @Expose
    private String imgA = "";
    @Column
    @Expose
    private String imgB = "";
    @Column
    @Expose
    private String imgC = "";
    @Column
    @Expose
    private String imgD = "";
    @Column
    @Expose
    private String imgE = "";
    @Column
    @Expose
    private String url = "";
    @Column
    @Expose
    private String outpacement = "";
    @Column
    @Expose
    private int finalAtk = 0;
    @Column
    @Expose
    private int finalHp = 0;
    @Column
    @Expose
    private int star = 0;
    @Column
    @Expose
    private String home = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJpName() {
        return jpName;
    }

    public void setJpName(String jpName) {
        this.jpName = jpName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOriginAtk() {
        return originAtk;
    }

    public void setOriginAtk(int originAtk) {
        this.originAtk = originAtk;
    }

    public int getOriginHp() {
        return originHp;
    }

    public void setOriginHp(int originHp) {
        this.originHp = originHp;
    }

    public int getArtsHits() {
        return artsHits;
    }

    public void setArtsHits(int artsHits) {
        this.artsHits = artsHits;
    }

    public int getBusterHits() {
        return busterHits;
    }

    public void setBusterHits(int busterHits) {
        this.busterHits = busterHits;
    }

    public int getQuickHits() {
        return quickHits;
    }

    public void setQuickHits(int quickHits) {
        this.quickHits = quickHits;
    }

    public int getExtraHits() {
        return extraHits;
    }

    public void setExtraHits(int extraHits) {
        this.extraHits = extraHits;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getStarCount() {
        return starCount;
    }

    public void setStarCount(double starCount) {
        this.starCount = starCount;
    }

    public double getDieReate() {
        return dieReate;
    }

    public void setDieReate(double dieReate) {
        this.dieReate = dieReate;
    }

    public double getCritWeight() {
        return critWeight;
    }

    public void setCritWeight(double critWeight) {
        this.critWeight = critWeight;
    }

    public double getNpGetQ() {
        return npGetQ;
    }

    public void setNpGetQ(double npGetQ) {
        this.npGetQ = npGetQ;
    }

    public double getNpGetA() {
        return npGetA;
    }

    public void setNpGetA(double npGetA) {
        this.npGetA = npGetA;
    }

    public double getNpGetB() {
        return npGetB;
    }

    public void setNpGetB(double npGetB) {
        this.npGetB = npGetB;
    }

    public double getNpGetEx() {
        return npGetEx;
    }

    public void setNpGetEx(double npGetEx) {
        this.npGetEx = npGetEx;
    }

    public double getNpGetTreasure() {
        return npGetTreasure;
    }

    public void setNpGetTreasure(double npGetTreasure) {
        this.npGetTreasure = npGetTreasure;
    }

    public double getNpGetInjred() {
        return npGetInjred;
    }

    public void setNpGetInjred(double npGetInjred) {
        this.npGetInjred = npGetInjred;
    }

    public String getGrowth() {
        return growth;
    }

    public void setGrowth(String growth) {
        this.growth = growth;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getImgA() {
        return imgA;
    }

    public void setImgA(String imgA) {
        this.imgA = imgA;
    }

    public String getImgB() {
        return imgB;
    }

    public void setImgB(String imgB) {
        this.imgB = imgB;
    }

    public String getImgC() {
        return imgC;
    }

    public void setImgC(String imgC) {
        this.imgC = imgC;
    }

    public String getImgD() {
        return imgD;
    }

    public void setImgD(String imgD) {
        this.imgD = imgD;
    }

    public String getImgE() {
        return imgE;
    }

    public void setImgE(String imgE) {
        this.imgE = imgE;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOutpacement() {
        return outpacement;
    }

    public void setOutpacement(String outpacement) {
        this.outpacement = outpacement;
    }

    public int getFinalAtk() {
        return finalAtk;
    }

    public void setFinalAtk(int finalAtk) {
        this.finalAtk = finalAtk;
    }

    public int getFinalHp() {
        return finalHp;
    }

    public void setFinalHp(int finalHp) {
        this.finalHp = finalHp;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "FateInfo{" +
                "name='" + name + '\'' +
                ", jpName='" + jpName + '\'' +
                ", enName='" + enName + '\'' +
                ", type='" + type + '\'' +
                ", originAtk=" + originAtk +
                ", originHp=" + originHp +
                ", artsHits=" + artsHits +
                ", busterHits=" + busterHits +
                ", quickHits=" + quickHits +
                ", extraHits=" + extraHits +
                ", author='" + author + '\'' +
                ", cv='" + cv + '\'' +
                ", attribute='" + attribute + '\'' +
                ", sex='" + sex + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", starCount=" + starCount +
                ", dieReate=" + dieReate +
                ", critWeight=" + critWeight +
                ", npGetQ=" + npGetQ +
                ", npGetA=" + npGetA +
                ", npGetB=" + npGetB +
                ", npGetEx=" + npGetEx +
                ", npGetTreasure=" + npGetTreasure +
                ", npGetInjred=" + npGetInjred +
                ", growth='" + growth + '\'' +
                ", from='" + from + '\'' +
                ", feature='" + feature + '\'' +
                ", imgA='" + imgA + '\'' +
                ", imgB='" + imgB + '\'' +
                ", imgC='" + imgC + '\'' +
                ", imgD='" + imgD + '\'' +
                ", imgE='" + imgE + '\'' +
                ", url='" + url + '\'' +
                ", outpacement='" + outpacement + '\'' +
                ", finalAtk=" + finalAtk +
                ", finalHp=" + finalHp +
                ", star=" + star +
                ", home='" + home + '\'' +
                '}';
    }
}
