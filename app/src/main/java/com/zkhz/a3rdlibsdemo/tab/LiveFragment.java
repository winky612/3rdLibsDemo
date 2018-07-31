package com.zkhz.a3rdlibsdemo.tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkhz.a3rdlibsdemo.R;


/**
 * Created by wk on 2018/7/26 0026
 */
public class LiveFragment extends Fragment {

    public static LiveFragment newInstance() {

        LiveFragment fragment = new LiveFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_live,null);
    }
}
