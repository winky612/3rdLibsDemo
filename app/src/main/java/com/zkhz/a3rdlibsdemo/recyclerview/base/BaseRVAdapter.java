package com.zkhz.a3rdlibsdemo.recyclerview.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public abstract class BaseRVAdapter<T> extends RecyclerView.Adapter<BaseRVAdapter.BaseViewHolder<T>> {

    private List<T> list;

    public BaseRVAdapter(List<T> list) {
        this.list = list;
    }

    public BaseRVAdapter() {
    }


    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<T> holder, int position) {

        holder.bindData(list.get(position));

    }
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

        public BaseViewHolder(ViewGroup parent, int layoutId) {
            super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));
        }


        public abstract void bindData(T data);
    }
}
