package com.zkhz.a3rdlibsdemo.room.entity;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


/**
 * @ Entity注释将数据类标记为可持久化实体。必须使用@PrimaryKey注释对至少一个类字段进行注释。
 */

@Entity
public class User {

    @PrimaryKey
    @NonNull
    public String id;
    public String name;
    public String lastName;
    public int age;
}
