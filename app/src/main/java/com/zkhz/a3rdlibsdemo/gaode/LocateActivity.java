package com.zkhz.a3rdlibsdemo.gaode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/4 0004.
 */

public class LocateActivity extends AppCompatActivity {

    @BindView(R.id.tv_city)
    TextView tvCity;

    private LocHelper locHelper;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption mOption = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //key b34e75c285989c6b85ea713ba0e14f85

        setContentView(R.layout.activity_locate);
        ButterKnife.bind(this);


//        locHelper=new LocHelper();
//        locHelper.setTextView(tvCity);
//        locHelper.initLoca(LocateActivity.this);

        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        // 设置定位监听
        locationClient.setLocationListener(locationListener);

        mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(false);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true

        // 设置定位参数
        locationClient.setLocationOption(mOption);
        // 启动定位
        locationClient.startLocation();



    }

    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {

                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if (location.getErrorCode() == 0) {

                    //解析location获取相应内容。
                    // 经度
                    location.getLongitude();
                    //纬度
                    location.getLatitude();

                    location.getCountry();
                    location.getProvince();
                    location.getCity();
                    location.getCityCode();//城市编码
                    location.getDistrict();
                    location.getAdCode();//区域 码
                    location.getAddress();
                    location.getPoiName();//兴趣点

                  tvCity.setText(location.getCity());
                    stopLocation();
                } else {
                    //定位失败
                    location.getErrorCode();
                    location.getErrorInfo();
                    location.getLocationDetail();

                    tvCity.setText(location.getErrorCode());
                }
            } else {

                tvCity.setText("定位失败，loc is null");
            }
        }
    };

    /**
     * 停止定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void stopLocation() {
        // 停止定位
        locationClient.stopLocation();
    }

    /**
     * 销毁定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void destroyLocation() {
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            mOption = null;
        }
    }



}
