package com.zkhz.a3rdlibsdemo.recyclerview.base;

import android.support.v7.widget.RecyclerView;

/**
 * Created by Administrator on 2018/4/13 0013.
 *
 * rv的itemview点击事件
 */

public interface OnItemClickListener {

    void onItemClick(RecyclerView.ViewHolder holder);
}
