package com.zkhz.a3rdlibsdemo.recyclerview.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.zkhz.a3rdlibsdemo.R;
import com.zkhz.a3rdlibsdemo.recyclerview.base.BaseActivity;
import com.zkhz.a3rdlibsdemo.recyclerview.base.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wk on 2018/7/14 0014
 */
public class MineActivity extends BaseActivity {
    @BindView(R.id.rv_setting)
    RecyclerView rvSetting;

    private List<MineData> datas;
    private MineData headData;
    private MineAdapter adapter;


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_setting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        initData();

        LinearLayoutManager manager = new LinearLayoutManager(MineActivity.this);
        adapter = new MineAdapter();
        adapter.setList(datas);
        rvSetting.setLayoutManager(manager);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {

                int txtId = datas.get(holder.getAdapterPosition()).getTxt();
                switch (txtId){
                    case R.string.my_wallet:
                        Toast.makeText(MineActivity.this, ""+txtId, Toast.LENGTH_SHORT).show();
                        break;

                }


            }
        });

        //头像点击事件
        adapter.setOnPortraitClickListener(new MineAdapter.onPortraitClickListener() {
            @Override
            public void onPortraitClick() {
                Toast.makeText(MineActivity.this, "Portrait", Toast.LENGTH_SHORT).show();
            }
        });


        rvSetting.setAdapter(adapter);



    }


    @Override
    protected void initData() {
        super.initData();

        headData = new MineData(R.drawable.ic_launcher_background,"hahahahah",R.mipmap.icon_male,"鸥乐号:123455",16);

        datas = new ArrayList<>();
        datas.add(new MineData(R.drawable.ic_launcher_background,"hahahahah",R.mipmap.icon_male,"鸥乐号:123455",16));
        datas.add(new MineData(R.drawable.ic_launcher_background,R.string.my_wallet));
        datas.add(new MineData(R.drawable.ic_launcher_background,R.string.my_focus));
        datas.add(new MineData(R.drawable.ic_launcher_background,R.string.my_msg));


    }
}
