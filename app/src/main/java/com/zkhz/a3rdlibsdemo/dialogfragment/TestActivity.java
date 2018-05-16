package com.zkhz.a3rdlibsdemo.dialogfragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/9 0009.
 */

public class TestActivity extends AppCompatActivity implements DialogFragment2.LoginListener {
    @BindView(R.id.btn_df1)
    Button btnDf1;
    @BindView(R.id.btn_df2)
    Button btnDf2;
    @BindView(R.id.btn_df3)
    Button btnDf3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_df1, R.id.btn_df2,R.id.btn_df3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_df1:


                //显示dialog
//                DialogFragment1 dialogFragment1 = new DialogFragment1();
//                dialogFragment1.show(getFragmentManager(), "DialogFragment1");

                showDialogInDifferentScreen();

                break;
            case R.id.btn_df2:

                DialogFragment2 dialogFragment2 = new DialogFragment2();

                //第二个参数 "missiles" 是系统用于保存片段状态并在必要时进行恢复的唯一标记名称。 该标记还允许您通过调用 findFragmentByTag() 获取片段的句柄
                dialogFragment2.show(getFragmentManager(), "DialogFragment2");


//                dialogFragment2.setOnLoginListener(new DialogFragment2.LoginListener() {
//                    @Override
//                    public void onLogin(String username, String passwd) {
//                        Toast.makeText(TestActivity.this, "帐号：" + username + ",  密码 :" + passwd, Toast.LENGTH_SHORT).show();
//                    }
//                });

            case R.id.btn_df3:


                break;


        }

    }

    @Override
    public void onLogin(String username, String passwd) {
        Toast.makeText(this, "帐号：" + username + ",  密码 :" + passwd,
                Toast.LENGTH_SHORT).show();
    }


    /**
     * DialogFragment做屏幕适配
     *
     * 根据屏幕尺寸决定将片段显示为对话框还是全屏 UI
     *
     * 
     */
    public void showDialogInDifferentScreen() {
        FragmentManager fragmentManager = getFragmentManager();
        DialogFragment1 newFragment = new DialogFragment1();

        //指定当前设备是否应该使用应用的大布局设计（进而将此片段显示为对话框，而不是全屏显示）
        boolean mIsLargeLayout = getResources().getBoolean(R.bool.large_layout);
        Log.e("TAG", mIsLargeLayout + "");
        if (mIsLargeLayout) {
            // The device is using a large layout, so show the fragment as a dialog
            newFragment.show(fragmentManager, "dialog");
        } else {
            // The device is smaller, so show the fragment fullscreen
            FragmentTransaction transaction = fragmentManager
                    .beginTransaction();
            // For a little polish, specify a transition animation
            transaction
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // To make it fullscreen, use the 'content' root view as the container for the fragment, which is always the root view for the activity
            transaction.add(R.id.container, newFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }


}
