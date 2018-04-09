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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_df1,R.id.btn_df2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_df1:


                //显示dialog
//                DialogFragment1 dialogFragment1 = new DialogFragment1();
//                dialogFragment1.show(getFragmentManager(), "DialogFragment1");

                showDialogInDifferentScreen(view);

                break;
            case R.id.btn_df2:
                DialogFragment2 dialogFragment2=new DialogFragment2();
                dialogFragment2.show(getFragmentManager(),"DialogFragment2");

        }

    }

    @Override
    public void onLogin(String username, String passwd) {
        Toast.makeText(this, "帐号：" + username + ",  密码 :" + passwd,
                Toast.LENGTH_SHORT).show();
    }


    /**
     * DialogFragment做屏幕适配
     * @param view
     */
    public void showDialogInDifferentScreen(View view){
        FragmentManager fragmentManager = getFragmentManager();
        DialogFragment1 newFragment = new DialogFragment1();

        boolean mIsLargeLayout = getResources().getBoolean(R.bool.large_layout) ;
        Log.e("TAG", mIsLargeLayout+"");
        if (mIsLargeLayout ) {
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
            transaction.replace(R.id.container, newFragment)
                    .commit();
        }
    }


}
