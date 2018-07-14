package com.zkhz.a3rdlibsdemo.recyclerview.mine;

/**
 * Created by wk on 2018/7/14 0014
 */
public class MineData {

    private int icon;
    private int txt;

    private int portrait;
    private String name;
    private int sex;
    private String olNum;
    private int age;

    public MineData(int icon, int txt) {
        this.icon = icon;
        this.txt = txt;
    }

    public MineData(int portrait, String name, int sex, String olNum, int age) {
        this.portrait = portrait;
        this.name = name;
        this.sex = sex;
        this.olNum = olNum;
        this.age = age;
    }

    public MineData() {
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getTxt() {
        return txt;
    }

    public void setTxt(int txt) {
        this.txt = txt;
    }

    public int getPortrait() {
        return portrait;
    }

    public void setPortrait(int portrait) {
        this.portrait = portrait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getOlNum() {
        return olNum;
    }

    public void setOlNum(String olNum) {
        this.olNum = olNum;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
