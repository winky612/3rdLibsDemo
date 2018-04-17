package com.zkhz.a3rdlibsdemo.juherv;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zkhz.a3rdlibsdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/11 0011.
 */

public class NewsRVAdapter extends RecyclerView.Adapter<NewsRVAdapter.MyHolder> {

    private List<Data.ResultBean.DataBean> list;
    private OnItemClickListener onItemClickListener;

    public NewsRVAdapter() {
    }

    public NewsRVAdapter(List<Data.ResultBean.DataBean> list) {
        this.list = list;
    }

    public List<Data.ResultBean.DataBean> getList() {
        return list;
    }

    public void setList(List<Data.ResultBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();//更新界面显示的数据
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        Glide.with(holder.itemView.getContext()).load(list.get(position).getUrl()).into(holder.pic);

        if (onItemClickListener!=null){
            holder.pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.pic,holder.getLayoutPosition());
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public interface OnItemClickListener {
        void onItemClick(View view,int position);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;

    }

    class MyHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView pic;


        public MyHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_title);
            pic = itemView.findViewById(R.id.iv_pic);

        }
    }
}
