package com.zkhz.a3rdlibsdemo.mvpdemo.pracmvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zkhz.a3rdlibsdemo.R;
import com.zkhz.a3rdlibsdemo.mvpdemo.pracmvp.presenter.IPresenter;
import com.zkhz.a3rdlibsdemo.mvpdemo.pracmvp.presenter.UserPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class PracMVPDemoActivity extends AppCompatActivity implements IUserView{
    @BindView(R.id.edt_id)
    EditText edtId;
    @BindView(R.id.edt_fn)
    EditText edtFn;
    @BindView(R.id.edt_ln)
    EditText edtLn;
    @BindView(R.id.btn_write)
    Button btnWrite;
    @BindView(R.id.btn_read)
    Button btnRead;

    IPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pracmvp_demo);
        ButterKnife.bind(this);

        //获取p层的接口实例，并且传入此v层,为了调用p层里的实现业务逻辑的方法
        presenter=new UserPresenter(this);


    }

    @OnClick({R.id.btn_write, R.id.btn_read})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_write:
                presenter.saveUser();
                break;
            case R.id.btn_read:
                presenter.loadUser();
                break;
        }
    }

    @Override
    public int getId() {
        return Integer.parseInt(edtId.getText().toString());
    }

    @Override
    public String getUserName() {
        return edtFn.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return edtLn.getText().toString();
    }

    @Override
    public void setUserName(String userName) {

        edtFn.setText(userName);

    }

    @Override
    public void setUserPassword(String password) {

        edtLn.setText(password);

    }
}
