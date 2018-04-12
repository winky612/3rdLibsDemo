package com.zkhz.a3rdlibsdemo.recyclerview.animal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zkhz.a3rdlibsdemo.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class AnimalRVAdapter extends RecyclerView.Adapter<AnimalRVAdapter.MyAnimalHolder> {

    private List<Animal> list;

    public AnimalRVAdapter(List<Animal> list) {
        this.list = list;
    }

    public AnimalRVAdapter() {
    }

    public List<Animal> getList() {
        return list;
    }

    public void setList(List<Animal> list) {
        this.list = list;
    }

    @Override
    public MyAnimalHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new MyAnimalHolder(parent,R.layout.item_rv);

    }

    @Override
    public void onBindViewHolder(MyAnimalHolder holder, int position) {

        holder.textView.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    class MyAnimalHolder extends RecyclerView.ViewHolder{

        private TextView textView;


        public MyAnimalHolder(ViewGroup parent,int layoutId) {
            super(LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false));

            textView=itemView.findViewById(R.id.tv_name);

        }
    }
}
