package com.zkhz.a3rdlibsdemo.recyclerview.animal;

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

public class AnimalRVActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    private List<Animal> list;
    private AnimalRVAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        ButterKnife.bind(this);

        addData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AnimalRVActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(linearLayoutManager);
        adapter=new AnimalRVAdapter();
        adapter.setList(list);
        rv.setAdapter(adapter);


    }

    private void addData() {
        list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(new Animal("CAT"));
            list.add(new Animal("狗狗"));
            list.add(new Animal("虫虫"));
        }
    }
}
