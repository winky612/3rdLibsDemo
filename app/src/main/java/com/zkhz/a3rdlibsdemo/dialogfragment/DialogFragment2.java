package com.zkhz.a3rdlibsdemo.dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.zkhz.a3rdlibsdemo.R;

/**
 * Created by Administrator on 2018/4/9 0009.
 *
 * onCreateDialog即利用AlertDialog或者Dialog创建出Dialog
 *
 */

public class DialogFragment2 extends DialogFragment {

    private EditText mUsername;
    private EditText mPassword;

//    private LoginListener loginListener;

    public interface LoginListener{
        void onLogin(String username,String passwd);
    }

//    public void setOnLoginListener(LoginListener loginListener){
//
//        this.loginListener=loginListener;
//
//
//    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        //
//        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
//        builder.setTitle("支付提示")
//                .setMessage("买否?")
//                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        Toast.makeText(getActivity(), "买买买~", Toast.LENGTH_SHORT).show();
//
//                    }
//                })
//                .setNegativeButton("no",null);
//
//
//        return builder.create();




        //Dialog向activity传递数据
        LayoutInflater layoutInflater=getActivity().getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.fragment_oncreatedialog,null);
        mUsername=view.findViewById(R.id.edt_name);
        mPassword=view.findViewById(R.id.edt_passwd);

        AlertDialog dialog=new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //拿到username和password的引用，在点击登录的时候，把activity强转为我们自定义的接口：LoginInputListener，然后将用户输入的数据返回。
                        LoginListener loginListener= (LoginListener) getActivity();
                        loginListener.onLogin(mUsername.getText().toString(), mPassword.getText().toString());

                    }
                })
                .setNegativeButton("Cancel",null)
                .create();
        return dialog;
    }
}
