package com.zkhz.a3rdlibsdemo.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.zkhz.a3rdlibsdemo.R;
import com.zkhz.a3rdlibsdemo.recyclerview.base.BaseRVAdapter;
import com.zkhz.a3rdlibsdemo.recyclerview.fruit.Fruit;
import com.zkhz.a3rdlibsdemo.recyclerview.fruit.FruitRVAdapter2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class RVActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    private List<Fruit> fruits = new ArrayList<>();
    private FruitRVAdapter2 adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        ButterKnife.bind(this);

        addData();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(RVActivity.this,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(linearLayoutManager);
        adapter=new FruitRVAdapter2();
        adapter.setList(fruits);
        rv.setAdapter(adapter);


       adapter.setOnFruitClickListener(new FruitRVAdapter2.OnFruitClickListener() {
           @Override
           public void onPicClick(RecyclerView.ViewHolder holder) {

               Toast.makeText(RVActivity.this, " PIC"+fruits.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onNameClick(BaseRVAdapter.BaseViewHolder holder) {
               Toast.makeText(RVActivity.this, " NAME"+fruits.get(holder.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();


           }

           @Override
           public void OnItemClick(RecyclerView.ViewHolder holder) {

               Toast.makeText(RVActivity.this, " item"+fruits.get(holder.getAdapterPosition()), Toast.LENGTH_SHORT).show();


           }
       });






    }


    //假数据
    private void addData() {

        for (int i = 0; i < 3; i++) {

            fruits.add(new Fruit("apple","https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2121597356,1237340568&fm=27&gp=0.jpg"));
            fruits.add(new Fruit("pine","https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4176539679,2071232867&fm=200&gp=0.jpg"));
            fruits.add(new Fruit("orange","https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523512247984&di=8d8e86ba89c2bb4b6d19099a3e480985&imgtype=0&src=http%3A%2F%2Fs2.sinaimg.cn%2Fmw690%2F001KuZB1gy72tooNI9r31%26690"));

        }
    }
}
