package com.zkhz.a3rdlibsdemo.recyclerview.fruit;

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
 * Created by Administrator on 2018/4/12 0012.
 */

public class FruitRVAdapter extends RecyclerView.Adapter<FruitRVAdapter.FruitHolder> {

    private OnPicClickListener listener;


    private List<Fruit> list;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public FruitRVAdapter(List list) {

        this.list = list;
    }


    public void setOnPicClickListener(OnPicClickListener listener) {
        this.listener = listener;

    }


    @NonNull
    @Override
    public FruitHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_fruit, parent, false);
        FruitHolder fruitHolder = new FruitHolder(itemView);
        return fruitHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FruitHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        Glide.with(holder.itemView).load(list.get(position).getPic()).into(holder.pic);

        if (listener!=null){
            holder.pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnPicClick(holder);

                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class FruitHolder extends RecyclerView.ViewHolder {

        private ImageView pic;
        private TextView name;

        public FruitHolder(View itemView) {
            super(itemView);

            pic = itemView.findViewById(R.id.iv_fruit);
            name = itemView.findViewById(R.id.tv_fruit);

        }
    }

    public interface OnPicClickListener {
        void OnPicClick(RecyclerView.ViewHolder holder);
    }
}
