package com.zkhz.a3rdlibsdemo.blur;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.wonderkiln.blurkit.BlurKit;
import com.wonderkiln.blurkit.BlurLayout;
import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public class BlurDemoActivity extends AppCompatActivity {


    @BindView(R.id.tv_blur)
    TextView tvBlur;
    @BindView(R.id.bl2)
    BlurLayout bl2;
    @BindView(R.id.text)
    TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blurdemo);
        ButterKnife.bind(this);

        bl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bl2.invalidate();
            }
        });


        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BlurKit.getInstance().blur(v,1);没效果
                BlurKit.getInstance().fastBlur(v,10,0.51f);//没效果
                Toast.makeText(BlurDemoActivity.this, "You Click~", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
