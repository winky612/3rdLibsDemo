package com.zkhz.a3rdlibsdemo.coretopics.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.ButterKnife;

/**
 * activity 与 fragment间通信:
 * 1.在片段内定义一个回调接口
 * 2.要求宿主 Activity 实现它
 * 3.为确保宿主 Activity 实现此接口，fragment的 onAttach()回调方法(系统在向 Activity 添加片段时调用的方法)会通过转换传递到 onAttach()中的 Activity 来实例化OnArticleSelectedListener 的实例
 */


public class ListFrgmntDemoActivity extends AppCompatActivity implements LeftListFragment.onTitleSelectedListener {

    RightListFragment fgRight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listfrgmnt_demo);
        ButterKnife.bind(this);

        LeftListFragment leftListFragment = new LeftListFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.left_container,leftListFragment);
        transaction.commit();

        fgRight = (RightListFragment) getSupportFragmentManager().findFragmentById(R.id.fg_right);

    }


    @Override
    public void onTitleSelected(String str) {

        Toast.makeText(this, "str", Toast.LENGTH_SHORT).show();

    }
}
