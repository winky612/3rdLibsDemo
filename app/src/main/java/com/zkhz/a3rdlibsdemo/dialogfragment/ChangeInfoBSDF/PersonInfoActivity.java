package com.zkhz.a3rdlibsdemo.dialogfragment.ChangeInfoBSDF;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wk on 2018/7/22.
 */

public class PersonInfoActivity extends AppCompatActivity implements ChangeInfoBottomSheetFragment.onChangeClickListener {
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_name, R.id.tv_sex})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tv_name:


                //nice
                String title = tvName.getText().toString();
                ChangeInfoBottomSheetFragment nameDialog = ChangeInfoBottomSheetFragment.newInstance(title);
                nameDialog.show(getSupportFragmentManager(), "nameDialog");

                break;
            case R.id.tv_sex:

                //bad
                String titleSex = tvName.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString("sex",titleSex);
                ChangeInfoBottomSheetFragment sexDialog = new ChangeInfoBottomSheetFragment();
                sexDialog.setArguments(bundle);
                sexDialog.show(getSupportFragmentManager(), "sexDialog");
                break;
        }
    }

    @Override
    public void onChangeClick(String content) {

        tvName.setText(content);

    }
}
