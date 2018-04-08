package com.zkhz.a3rdlibsdemo.gaode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.Toast;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;
import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/4 0004.
 */

public class NaviActivity extends AppCompatActivity {
    @BindView(R.id.btn_guide)
    Button btnGuide;

//    private AmapTTSController amapTTSController;


    LatLng p2 = new LatLng(39.917337, 116.397056);//故宫博物院
    LatLng p3 = new LatLng(39.904556, 116.427231);//北京站

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_locate);
        ButterKnife.bind(this);



    }

    @OnClick(R.id.btn_guide)
    public void onViewClicked() {

        AmapNaviParams params = new AmapNaviParams(new Poi("北京站", p3, ""), null, new Poi("故宫博物院", p2, ""), AmapNaviType.DRIVER);
        params.setUseInnerVoice(true);
        AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), new AmapNaviParams(new Poi("北京站", p3, ""), null, new Poi("故宫博物院", p2, ""), AmapNaviType.DRIVER), new INaviInfoCallback() {
            @Override
            public void onInitNaviFailure() {
                Toast.makeText(NaviActivity.this, "onInitNaviFailure", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onGetNavigationText(String s) {
                Toast.makeText(NaviActivity.this, "onGetNavigationText", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLocationChange(AMapNaviLocation aMapNaviLocation) {
                Toast.makeText(NaviActivity.this, "onLocationChange", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onArriveDestination(boolean b) {
                Toast.makeText(NaviActivity.this, "onArriveDestination", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStartNavi(int i) {
                Toast.makeText(NaviActivity.this, "onStartNavi", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCalculateRouteSuccess(int[] ints) {
                Toast.makeText(NaviActivity.this, "onCalculateRouteSuccess", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCalculateRouteFailure(int i) {
                Toast.makeText(NaviActivity.this, "onCalculateRouteFailure", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopSpeaking() {
                Toast.makeText(NaviActivity.this, "onStopSpeaking", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onReCalculateRoute(int i) {
                Toast.makeText(NaviActivity.this, "onReCalculateRoute", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onExitPage(int i) {
                Toast.makeText(NaviActivity.this, "onExitPage", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        amapTTSController.destroy();
    }

    /**
     * 返回键处理事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            System.exit(0);// 退出程序
        }
        return super.onKeyDown(keyCode, event);
    }


}
