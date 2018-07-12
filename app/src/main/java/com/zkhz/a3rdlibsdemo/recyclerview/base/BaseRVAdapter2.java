package com.zkhz.a3rdlibsdemo.recyclerview.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2018/4/23 0023.
 */

public abstract class BaseRVAdapter2<T> extends RecyclerView.Adapter<BaseRVAdapter2.BaseViewHolder2<T>> {

    private List<T> list;
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public BaseRVAdapter2(List<T> list) {
        this.list = list;
    }

    public BaseRVAdapter2() {
    }

    @Override
    public void onBindViewHolder(BaseViewHolder2<T> holder, int position) {

        holder.bindData(list.get(position));

    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();

    }


    public static abstract class BaseViewHolder2<T> extends RecyclerView.ViewHolder {

        public BaseViewHolder2(ViewGroup parent, int layoutId, final OnItemClickListener listener) {
            super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){

                    listener.onItemClick(BaseViewHolder2.this);
                    }
                }
            });

        }


        public abstract void bindData(T data);

    }
}
