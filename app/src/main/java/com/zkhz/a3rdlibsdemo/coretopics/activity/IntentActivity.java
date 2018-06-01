package com.zkhz.a3rdlibsdemo.coretopics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntentActivity extends AppCompatActivity {
    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;
    @BindView(R.id.btn_3)
    Button btn3;
    @BindView(R.id.btn_4)
    Button btn4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notifdemo1);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_1:

                intent01();

                break;
            case R.id.btn_2:

                intent02();
                break;
            case R.id.btn_3:

                intent03();
                break;
            case R.id.btn_4:
                break;
        }
    }


    /**
     * Intent对象指定要启动的确切activity或描述您要执行的操作的类型（并且系统会为您选择适当的activity，甚至可能来自别的的应用程序）。
     * Intent对象还可以携带少量数据供启动的活动使用
     */
    private void intent01() {

        Intent intent = new Intent(Intent.ACTION_VIEW);
        startActivity(intent);
    }


    /**
     *
     */
    private void intent02() {

        String[] recipientArray = {"wangk0612@126.com", "1205213767@qq.com"};
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipientArray);
        startActivity(intent);

    }


    /**
     * 生命周期回调的顺序已被很好地定义，特别是当两个活动处于同一个进程（应用程序）并且一个正在启动另一个时。
     * 以下是活动A启动活动B时发生的操作顺序：活动A的onPause（）方法执行。
     * Activity B的onCreate（），onStart（）和onResume（）方法按顺序执行。 （活动B现在有用户焦点。）
     * 然后，如果活动A在屏幕上不再可见，则执行其onStop（）方法。
     * 这种可预测的生命周期回调序列允许您管理从一个活动到另一个活动的信息转换。
     */
    private void intent03() {

        Intent intent = new Intent();
        intent.setAction("com.zkhz.a3rdlibsdemo.activity.ActionCall");
        startActivity(intent);

    }
}
