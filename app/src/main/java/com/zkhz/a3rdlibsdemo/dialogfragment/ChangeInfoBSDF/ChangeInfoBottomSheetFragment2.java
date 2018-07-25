package com.zkhz.a3rdlibsdemo.dialogfragment.ChangeInfoBSDF;

import android.os.Bundle;

import com.zkhz.a3rdlibsdemo.R;

/**
 * Created by wk on 2018/7/24 0024
 */
public class ChangeInfoBottomSheetFragment2 extends BaseFragment {

    private static final String KEY_TITLE = "KEY_TITLE";


    @Override
    protected int getContentLayoutId() {
        return R.layout.dialog_bottom_change_info;
    }


    public static ChangeInfoBottomSheetFragment2 newInstance(String title){

        ChangeInfoBottomSheetFragment2 fragment2 = new ChangeInfoBottomSheetFragment2();
        Bundle args = new Bundle();
        args.putString(KEY_TITLE,title);
        return fragment2;
    }



}
