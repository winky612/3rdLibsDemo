package com.zkhz.a3rdlibsdemo.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Book {

    @PrimaryKey
    @NonNull
    public String id;
    public String title;
}
