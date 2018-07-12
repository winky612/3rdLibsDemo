package com.zkhz.a3rdlibsdemo.recyclerview.setting;

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
 * Created by wk on 2018/7/12.
 */

public class SettingActivtiy extends BaseActivity {
    @BindView(R.id.rv_setting)
    RecyclerView rvSetting;

    private SettingAdapter adapter;
    private List<SettingData> datas;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        initData();
        
        LinearLayoutManager manager = new LinearLayoutManager(SettingActivtiy.this);
        rvSetting.setLayoutManager(manager);
        adapter = new SettingAdapter();
        adapter.setList(datas);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {
                int titleId = datas.get(holder.getAdapterPosition()).getTitle();
                switch (titleId){
                    case R.string.about_us:
                        Toast.makeText(SettingActivtiy.this, ""+titleId, Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

        rvSetting.setAdapter(adapter);

    }


    @Override
    protected void initData() {
        super.initData();

        datas = new ArrayList<>();
        datas.add(new SettingData(R.drawable.ic_launcher_background,R.string.about_us));
        datas.add(new SettingData(R.drawable.ic_launcher_background,R.string.version_info));
        datas.add(new SettingData(R.drawable.ic_launcher_background,R.string.clear_cache));
        
        
    }
}
