package com.zkhz.a3rdlibsdemo.notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotifDemoActivity1 extends AppCompatActivity {

    @BindView(R.id.btn_1)
    Button btn1;

    private NotificationHelper notificationHelper;

    private static final int BUTTON_01 = 1100;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifdemo1);
        ButterKnife.bind(this);

        notificationHelper = new NotificationHelper(NotifDemoActivity1.this);


    }

    private void notifySample(int id, String title) {

        NotificationCompat.Builder builder = null;
        switch (id) {
            case BUTTON_01:
                builder = notificationHelper.getNotifyBuilder(NotifDemoActivity1.this,title, "button1");
                break;

        }

        if (builder != null){

            notificationHelper.notify(id,builder);

        }


    }

    @OnClick(R.id.btn_1)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_1:

                //创建简单通知
                notifySample(BUTTON_01, "BUTTON_01");
                break;

        }
    }
}
