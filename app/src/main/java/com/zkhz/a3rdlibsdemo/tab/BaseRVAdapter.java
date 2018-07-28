package com.zkhz.a3rdlibsdemo.tab;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseRVAdapter<T> extends RecyclerView.Adapter<BaseRVAdapter.BaseViewHolder<T>> {


    private List<T> list;
    public OnItemClickListener listener;

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
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder<T> holder, int position) {

        holder.bindData(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public static abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

        public BaseViewHolder(ViewGroup parent, int layoutId, final OnItemClickListener listener) {
            super(LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false));

            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){

                        listener.onItemClick(BaseViewHolder.this);

                    }
                }
            });
        }

        public abstract void bindData(T data);
    }


}
