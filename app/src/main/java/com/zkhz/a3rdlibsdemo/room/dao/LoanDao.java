package com.zkhz.a3rdlibsdemo.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zkhz.a3rdlibsdemo.room.entity.Loan;

@Dao
public interface LoanDao {

    @Insert()
    void insertLoan(Loan loan);

    @Query("delete from Loan")
    void deleteAll();
}
