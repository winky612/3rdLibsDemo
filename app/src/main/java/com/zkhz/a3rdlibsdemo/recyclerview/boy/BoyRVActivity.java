package com.zkhz.a3rdlibsdemo.recyclerview.boy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zkhz.a3rdlibsdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class BoyRVActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    private List<Boy> list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        ButterKnife.bind(this);

        addData();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(BoyRVActivity.this, LinearLayoutManager.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        BoyRVAdapter adapter = new BoyRVAdapter();
        adapter.setList(list);
        rv.setAdapter(adapter);

    }

    private void addData() {
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Boy("aa"));
            list.add(new Boy("bb"));
            list.add(new Boy("cc"));
        }
    }


}
