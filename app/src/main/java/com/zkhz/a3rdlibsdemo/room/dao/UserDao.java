package com.zkhz.a3rdlibsdemo.room.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zkhz.a3rdlibsdemo.room.entity.User;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;


/**
 * @Dao 用来注解一个接口或者抽象方法，该类的作用是提供访问数据库的方法。
 * 此类中带注释的方法用于在编译时生成相应的SQL
 * 这种抽象有助于减少需要维护的重复样板代码的数量。与运行时SQL不同，这些带注释的方法在编译时被解析和验证
 */

@Dao
public interface UserDao {

    @Query("select * from user")
    List<User> loadAllUsers();

    @Query("select * from user where id =:id")
    User loadUserById(int id);

    @Query("select * from user where name = :firstName and lastName = :lastName")
    List<User> findUserByNameAndLastName(String firstName, String lastName);

    @Insert(onConflict = IGNORE)
    void insertUser(User user);

    @Query("delete from User")
    void deleteAll();

    @Query("select * from User where :age == :age")
    List<User> findUsersYoungerThan(int age);


}
