package com.zkhz.a3rdlibsdemo.recyclerview.fruit;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class Fruit {

    private String name;
    private String pic;

    public Fruit() {
    }

    public Fruit(String name, String pic) {
        this.name = name;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
