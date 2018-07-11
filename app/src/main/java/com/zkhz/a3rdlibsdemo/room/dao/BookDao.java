package com.zkhz.a3rdlibsdemo.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;

import com.zkhz.a3rdlibsdemo.room.DateConverter;
import com.zkhz.a3rdlibsdemo.room.entity.Book;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
@TypeConverters(DateConverter.class)
public interface BookDao {

//    @Query("select * from Book" +
//            "inner join Loan on Loan.book_id == Book.id"+
//            "where Loan.user_id == :userId")

    @Query("SELECT * FROM Book " +
            "INNER JOIN Loan ON Loan.book_id LIKE Book.id " +
            "WHERE Loan.user_id LIKE :userId "
    )
    public List<Book> findBooksBorrowedByUser(String userId);

    @Insert(onConflict = IGNORE)
    void insertBook(Book book);

    @Query("delete from Book")
            void deleteAll();
}
