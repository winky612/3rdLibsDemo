package com.zkhz.a3rdlibsdemo.recyclerview.girl;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zkhz.a3rdlibsdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class GirlRVAdapter extends RecyclerView.Adapter<GirlRVAdapter.MyGirlHolder> {

    private List<Girl> list;
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }

    public GirlRVAdapter(List<Girl> list) {
        this.list = list;
    }

    public GirlRVAdapter() {
    }

    public List<Girl> getList() {
        return list;
    }

    public void setList(List<Girl> list) {
        this.list = list;
    }

    /*
    更新recyclerView视图
     */
    public void  notifyDataSetChanged(List<Girl> list){
        setList(list);
        notifyDataSetChanged();
    };

    @Override
    public MyGirlHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new MyGirlHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyGirlHolder holder, int position) {

        holder.textView.setText(list.get(position).getName());
        if (listener!=null){
            holder.textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(holder);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class MyGirlHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public MyGirlHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv_name);
        }
    }


    public interface OnItemClickListener {
        void OnItemClick(RecyclerView.ViewHolder holder);
    }
}
