package com.zkhz.a3rdlibsdemo.recyclerview.fruit;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zkhz.a3rdlibsdemo.R;
import com.zkhz.a3rdlibsdemo.recyclerview.base.BaseRVAdapter;
import com.zkhz.a3rdlibsdemo.recyclerview.base.OnItemClickListener;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public class FruitRVAdapter2 extends BaseRVAdapter<Fruit> {

    private OnFruitClickListener listener;


    public FruitRVAdapter2(OnFruitClickListener listener) {
        this.listener = listener;
    }

    public FruitRVAdapter2() {
    }

    public void setOnFruitClickListener(OnFruitClickListener listener){
        this.listener=listener;
    }

    @Override
    public BaseViewHolder<Fruit> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyFruitHolder(parent, R.layout.item_rv_fruit,listener);
    }


    class MyFruitHolder extends BaseViewHolder<Fruit>{

        private ImageView pic;
        private TextView name;


        public MyFruitHolder(ViewGroup parent, int layoutId, final OnFruitClickListener listener) {
            super(parent, layoutId, listener);

            pic=itemView.findViewById(R.id.iv_fruit);
            name=itemView.findViewById(R.id.tv_fruit);

//
//           itemView.setOnClickListener(new View.OnClickListener() {
//               @Override
//               public void onClick(View v) {
//                   if (null!=listener){
//                       switch (itemView.getId()){
//                           case R.id.tv_fruit:
//
//                               listener.onNameClick();
//                               break;
//                           case R.id.iv_fruit:
//
//                               listener.onPicClick();
//                               break;
//                       }
//                   }
//               }
//           });
//

            pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null!=listener){
                        listener.onPicClick(MyFruitHolder.this);
                    }
                }
            });

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null!=listener)
                    listener.onNameClick(MyFruitHolder.this);
                }
            });



        }

        @Override
        public void bindData(Fruit data) {

            Glide.with(itemView.getContext()).load(data.getPic()).into(pic);
            name.setText(data.getName());

        }
    }


    public interface OnFruitClickListener extends OnItemClickListener{
        void onPicClick(RecyclerView.ViewHolder holder);
        void onNameClick(BaseViewHolder holder);
    }
}
