package com.zkhz.a3rdlibsdemo.room.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zkhz.a3rdlibsdemo.R;
import com.zkhz.a3rdlibsdemo.room.AppDataBase;
import com.zkhz.a3rdlibsdemo.room.entity.User;
import com.zkhz.a3rdlibsdemo.room.utils.DatabaseInitializer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserActivity extends AppCompatActivity {
    @BindView(R.id.young_users_tv)
    TextView youngUsersTv;

    private AppDataBase dataBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.db_activity1);
        ButterKnife.bind(this);

        dataBase = AppDataBase.getInMemoryDataBase(getApplicationContext());

        populateDataBase();

        fetchData();



    }

    //填充数据
    private void populateDataBase() {

        DatabaseInitializer.populateSync(dataBase);
    }

    //读取数据
    private void fetchData() {

        // Note: this kind of logic should not be in an activity.
        StringBuilder sb=new StringBuilder();
        List<User> youngUsers = dataBase.userModel().findUsersYoungerThan(35);
        for (User youngUser:youngUsers){
            sb.append(String.format())

        }





    }

    @Override
    protected void onDestroy() {
        AppDataBase.destroyInstance();
        super.onDestroy();
    }
}
