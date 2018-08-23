package com.zkhz.a3rdlibsdemo.retrofit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.zkhz.a3rdlibsdemo.R;
import com.zkhz.a3rdlibsdemo.retrofit.ceobasenetwork.CEOClient;
import com.zkhz.a3rdlibsdemo.retrofit.ceobasenetwork.MyCallback;
import com.zkhz.a3rdlibsdemo.retrofit.ceobasenetwork.ReportData;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/4/24 0024.
 */

public class RetrofitActivity extends AppCompatActivity {

    private static final String TAG = "RetrofitActivity";
    private Call<ReportData> call;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        call = CEOClient.getApi().getReportData("2", "liuzhuo", "主播长的丑", "123", "app");

        call.enqueue(new MyCallback<ReportData>() {
            @Override
            protected void onSuccess(Call<ReportData> call, Response<ReportData> response) {

                ReportData reportData = response.body();
                int result = reportData.getData().getSaveReportInfo();
                if (0 == result) {

                    Toast.makeText(RetrofitActivity.this, result + "chenggong", Toast.LENGTH_SHORT).show();
                } else {

                    Toast.makeText(RetrofitActivity.this, "举报失败", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ReportData> call, Throwable t) {

                Toast.makeText(RetrofitActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.toString());

            }
        });
    }
}
