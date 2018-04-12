package com.zkhz.a3rdlibsdemo.recyclerview.boy;

import android.view.ViewGroup;
import android.widget.TextView;

import com.zkhz.a3rdlibsdemo.R;
import com.zkhz.a3rdlibsdemo.recyclerview.base.BaseRVAdapter;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class BoyRVAdapter extends BaseRVAdapter<Boy> {


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyBoyHolder(parent, R.layout.item_rv);
    }


    class MyBoyHolder extends BaseViewHolder<Boy>{

        private TextView textView;

        public MyBoyHolder(ViewGroup parent, int layoutId) {
            super(parent, layoutId);

            textView=itemView.findViewById(R.id.tv_name);
        }

        @Override
        public void bindData(Boy data) {
            textView.setText(data.getName());
        }

    }


}
