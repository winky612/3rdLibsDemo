package com.zkhz.a3rdlibsdemo.room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.zkhz.a3rdlibsdemo.room.DateConverter;

import java.util.Date;


/**
 * 因为SQLite是个关系型数据库，你能够指明两个对象的关系。虽然大多数ORM库支持entity对象引用其他的。Room明确的禁止这样
 *
 * 即使你不能使用直接关系，Room仍然允许你定义外键约束在两个entities中。
 *
 * 外键是十分强大的，因为它们允许你指明当引用的entity被更新后做什么。
 */

@Entity(foreignKeys = {@ForeignKey(entity = User.class,parentColumns = "id",childColumns = "user_id"),
                       @ForeignKey(entity = Book.class,parentColumns ="id",childColumns = "book_id")})
@TypeConverters(DateConverter.class)
public class Loan {

    @PrimaryKey
    @NonNull
    public String id;

    public Date startTime;

    public Date endTime;


    //默认字段名为数据库表名;可以通过@ColumnInfo修改列名称
    @ColumnInfo(name="book_id")
    public String bookId;

    @ColumnInfo(name="user_id")
    public String userId;



}
