package com.zkhz.a3rdlibsdemo.blur;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wonderkiln.blurkit.BlurKit;
import com.wonderkiln.blurkit.BlurLayout;
import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class BlurDemoActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.bl)
    BlurLayout bl;
    @BindView(R.id.tv_blur)
    TextView tvBlur;
    @BindView(R.id.tv_blur2)
    TextView tvBlur2;
    @BindView(R.id.bl2)
    BlurLayout bl2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blurdemo);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.image, R.id.tv_blur, R.id.tv_blur2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image:
                BlurKit.getInstance().blur(image,15);
                break;
            case R.id.tv_blur:
                bl2.invalidate();
                break;
            case R.id.tv_blur2:
                break;
        }
    }
}
