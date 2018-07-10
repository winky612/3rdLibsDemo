package com.zkhz.a3rdlibsdemo.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.zkhz.a3rdlibsdemo.room.dao.BookDao;
import com.zkhz.a3rdlibsdemo.room.dao.LoanDao;
import com.zkhz.a3rdlibsdemo.room.dao.UserDao;
import com.zkhz.a3rdlibsdemo.room.entity.Book;
import com.zkhz.a3rdlibsdemo.room.entity.Loan;
import com.zkhz.a3rdlibsdemo.room.entity.User;


/**
 * 你可以使用这个组件创建一个数据库holder。
 * 注解定义了一系列entities并且类的内容提供了一系列DAOs，它也是下层的主要连接 的访问点。
 * 注解的类应该是一个抽象的继承 RoomDatabase的类。在运行时，你能获得一个实例通过调用Room.databaseBuilder()或者 Room.inMemoryDatabaseBuilder()
 */

@Database(entities = {User.class, Book.class, Loan.class},version = 1)
public abstract class AppDataBase extends RoomDatabase{

    public static AppDataBase INSTANCE;

    public abstract UserDao userModel();
    public abstract BookDao bookModel();
    public abstract LoanDao loanModel();

    public static AppDataBase getInMemoryDataBase(Context context){
        if (INSTANCE == null){
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),AppDataBase.class)
                    .allowMainThreadQueries() //Room不允许在主线程中访问数据库除非你在建造器中调用allowMainThreadQueries()，因为它可能长时间的锁住UI
                    .build();

        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


}
