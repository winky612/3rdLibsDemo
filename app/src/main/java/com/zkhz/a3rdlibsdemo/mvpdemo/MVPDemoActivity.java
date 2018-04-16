package com.zkhz.a3rdlibsdemo.mvpdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class MVPDemoActivity extends AppCompatActivity implements IView {
    @BindView(R.id.edt_input)
    EditText edtInput;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    @BindView(R.id.tb_text)
    TextView tbText;

    private IPresenter mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvpdemo);
        ButterKnife.bind(this);
        
        initData();
    }

    private void initData() {
        mPresenter=new Presenter(this);
    }

    @OnClick(R.id.btn_submit)
    public void onViewClicked() {

        mPresenter.search();
    }

    @Override
    public String getInputString() {
        return edtInput.getText().toString();
    }

    @Override
    public void setResultString(String string) {

        tbText.setText(string);

    }
}
