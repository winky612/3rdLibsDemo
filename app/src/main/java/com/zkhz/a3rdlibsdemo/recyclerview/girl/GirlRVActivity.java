package com.zkhz.a3rdlibsdemo.recyclerview.girl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.zkhz.a3rdlibsdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class GirlRVActivity extends AppCompatActivity {
    @BindView(R.id.rv)
    RecyclerView rv;

    private List<Girl> list;
    private GirlRVAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        ButterKnife.bind(this);


        addData();
        GridLayoutManager layoutManager=new GridLayoutManager(GirlRVActivity.this,3);
        rv.setLayoutManager(layoutManager);
        adapter=new GirlRVAdapter();
        adapter.setList(list);
        rv.setAdapter(adapter);

        adapter.setOnItemClickListener(new GirlRVAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(RecyclerView.ViewHolder holder) {
                Toast.makeText(GirlRVActivity.this, "click~"+list.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void addData() {

        list=new ArrayList<>();
        for (int i = 0; i <6 ; i++) {
        list.add(new Girl("大大"));
        list.add(new Girl("凉凉"));
        list.add(new Girl("美美"));
        list.add(new Girl("明明"));

        }



    }


}
