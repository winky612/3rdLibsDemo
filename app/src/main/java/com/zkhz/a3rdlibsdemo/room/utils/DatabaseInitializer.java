package com.zkhz.a3rdlibsdemo.room.utils;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.zkhz.a3rdlibsdemo.room.AppDataBase;
import com.zkhz.a3rdlibsdemo.room.entity.Book;
import com.zkhz.a3rdlibsdemo.room.entity.Loan;
import com.zkhz.a3rdlibsdemo.room.entity.User;

import java.util.Calendar;
import java.util.Date;

public class DatabaseInitializer {

    private static final int DELAY_MILLIS = 500;


    //异步
    public static void populateAsync(final AppDataBase appDataBase){
        PopulateDbAsync populateDbAsync = new PopulateDbAsync(appDataBase);
        populateDbAsync.execute();
    }

    //同步
    public static void populateSync(@NonNull final AppDataBase db) {
        populateWithTestData(db);
    }

    //添加user
    private static User addUser(final AppDataBase appDataBase,String id,String name,String lastName,int age){
        User user = new User();
        user.id = id;
        user.age = age;
        user.name = name;
        user.lastName = lastName;
        appDataBase.userModel().insertUser(user);

        return user;

    }

    private static void addLoan(final AppDataBase db, final String id,
                                final User user, final Book book, Date from, Date to) {
        Loan loan = new Loan();
        loan.id = id;
        loan.bookId = book.id;
        loan.userId = user.id;
        loan.startTime = from;
        loan.endTime = to;
        db.loanModel().insertLoan(loan);
    }

    private static Book addBook(final AppDataBase db, final String id, final String title) {
        Book book = new Book();
        book.id = id;
        book.title = title;
        db.bookModel().insertBook(book);
        return book;
    }

    public static void populateWithTestData(AppDataBase appDataBase){

        appDataBase.userModel().deleteAll();
        appDataBase.bookModel().deleteAll();
        appDataBase.loanModel().deleteAll();

        User user1 = addUser(appDataBase, "1", "Jason", "Seaver", 40);
        User user2 = addUser(appDataBase, "2", "Mike", "Seaver", 12);
        addUser(appDataBase, "3", "Carol", "Seaver", 15);

        Book book1 = addBook(appDataBase, "1", "Dune");
        Book book2 = addBook(appDataBase, "2", "1984");
        Book book3 = addBook(appDataBase, "3", "The War of the Worlds");
        Book book4 = addBook(appDataBase, "4", "Brave New World");
        addBook(appDataBase, "5", "Foundation");

        Date today = getTodayPlusDays(0);
        Date yesterday = getTodayPlusDays(-1);
        Date twoDaysAgo = getTodayPlusDays(-2);
        Date lastWeek = getTodayPlusDays(-7);
        Date twoWeeksAgo = getTodayPlusDays(-14);

        try {
            addLoan(appDataBase,"1",user1,book1,twoWeeksAgo,lastWeek);
            Thread.sleep(DELAY_MILLIS);
            addLoan(appDataBase, "2", user2, book1, lastWeek, yesterday);
            Thread.sleep(DELAY_MILLIS);
            addLoan(appDataBase, "3", user2, book2, lastWeek, today);
            Thread.sleep(DELAY_MILLIS);
            addLoan(appDataBase, "4", user2, book3, lastWeek, twoDaysAgo);
            Thread.sleep(DELAY_MILLIS);
            addLoan(appDataBase, "5", user2, book4, lastWeek, today);
            Log.d("DB", "Added loans");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private static Date getTodayPlusDays(int daysAgo){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,daysAgo);
        return calendar.getTime();

    }

    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void>{

        private final AppDataBase dataBase;

        public PopulateDbAsync(AppDataBase dataBase) {
            this.dataBase = dataBase;
        }

        //这个方法中的所有代码都会在子线程中运行，我们应该在这里去处理所有的耗时任务。在这个方法中是不可以进行UI操作的，如果需要更新UI元素，比如说反馈当前任务的执行进度，可以调用publishProgress(Progress...)方法来完成
        @Override
        protected Void doInBackground(Void... voids) {
            populateWithTestData(dataBase);
            return null;
        }


    }
}
