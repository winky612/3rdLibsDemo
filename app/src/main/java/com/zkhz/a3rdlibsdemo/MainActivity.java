package com.zkhz.a3rdlibsdemo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    @BindView(R.id.btn_test)
    Button btnTest;
    @BindView(R.id.text)
    TextView text;

    // Whether there is a Wi-Fi connection.
    private static boolean wifiConnected = false;
    // Whether there is a mobile connection.
    private static boolean mobileConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_test)
    public void onViewClicked() {

        checkNetworkConnection();

    }


    /**
     * Check whether the device is connected, and if so, whether the connection
     * is wifi or mobile (it could be something else).
     */
    private void checkNetworkConnection() {
        // BEGIN_INCLUDE(connect)
        ConnectivityManager connMgr =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connMgr.getActiveNetworkInfo();
        if (activeInfo != null && activeInfo.isConnected()) {
            wifiConnected = activeInfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeInfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected) {
                Log.i(TAG, getString(R.string.wifi_connection));
                Toast.makeText(this, "wifi_connection", Toast.LENGTH_SHORT).show();
            } else if (mobileConnected) {
                Log.i(TAG, getString(R.string.mobile_connection));
                Toast.makeText(this, "mobile_connection", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.i(TAG, getString(R.string.no_wifi_or_mobile));
            Toast.makeText(this, "no_wifi_or_mobile", Toast.LENGTH_SHORT).show();
        }
        // END_INCLUDE(connect)
    }
}
