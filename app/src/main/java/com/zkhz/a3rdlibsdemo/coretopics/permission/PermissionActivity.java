package com.zkhz.a3rdlibsdemo.coretopics.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PermissionActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ASK_CALL_PHONE = 1100;
    public static String TAG = "PermissionActivity";


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

                requestPermission();

                break;
            case R.id.btn_2:
                break;
            case R.id.btn_3:
                break;
            case R.id.btn_4:
                break;
        }
    }


    private void requestPermission() {

        //检查您是否具有某项权限
        int checkPermission = ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CALL_PHONE);

        if (checkPermission != PackageManager.PERMISSION_GRANTED) {

            //是否应该显示请求权限的说明 如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true
            if (ActivityCompat.shouldShowRequestPermissionRationale(PermissionActivity.this, Manifest.permission.CALL_PHONE)) {

                Toast.makeText(this, "if you denied,you cannot use phone call", Toast.LENGTH_SHORT).show();
            } else {
                //申请授权
                ActivityCompat.requestPermissions(PermissionActivity.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_ASK_CALL_PHONE);
                Log.d(TAG, "requestPermission: " + "授权");
                return;
            }

        } else {

            dial();

            Log.d(TAG, "requestPermission: " + "");

        }


    }


    /**
     * 处理权限请求响应
     * <p>
     * 当应用请求权限时，系统将向用户显示一个对话框。当用户响应时，系统将调用应用的 onRequestPermissionsResult() 方法，向其传递用户响应。
     * 您的应用必须替换该方法，以了解是否已获得相应权限。回调会将您传递的相同请求代码传递给 requestPermissions()。
     * 例如，如果应用请求 CALL_PHONE 访问权限，则它可能采用以下回调方法：
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {

        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // Permission Granted
                    dial();

                } else {

                    // Permission Denied
                    Toast.makeText(this, "denied", Toast.LENGTH_SHORT).show();
                }
                return;
        }

        // other 'case' lines to check for other
        // permissions this app might request


    }

    private void dial() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("10086"));
        startActivity(intent);
    }
}
