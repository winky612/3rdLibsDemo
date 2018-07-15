package com.zkhz.a3rdlibsdemo.recyclerview.mine;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zkhz.a3rdlibsdemo.R;
import com.zkhz.a3rdlibsdemo.recyclerview.base.BaseRVAdapter;
import com.zkhz.a3rdlibsdemo.recyclerview.base.OnItemClickListener;

/**
 * Created by wk on 2018/7/14 0014
 */
public class MineAdapter extends BaseRVAdapter<MineData> {

    public onPortraitClickListener portraitClickListener;

    public void setOnPortraitClickListener(onPortraitClickListener listener){

        this.portraitClickListener = listener;

    }


    @Override
    public BaseViewHolder<MineData> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new MineViewHolderNormal(parent, R.layout.item_mine_ol_normal, listener);
        } else {
            return new MineViewHolderHeader(parent, R.layout.item_mine_rv_head, listener);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return getList().get(position).getTxt()==-1? 1 : 0;
    }

    //头
    class MineViewHolderHeader extends BaseViewHolder<MineData> {

        private ImageView portrait;
        private Button login;
        private TextView name;
        private ImageView sex;
        private TextView olNum;
        private TextView age;


        public MineViewHolderHeader(ViewGroup parent, int layoutId, final OnItemClickListener listener) {
            super(parent, layoutId, listener);

            portrait = itemView.findViewById(R.id.iv_portrait);
            login = itemView.findViewById(R.id.btn_register_load);
            name = itemView.findViewById(R.id.tv_nickname);
            sex = itemView.findViewById(R.id.iv_sex);
            olNum = itemView.findViewById(R.id.tv_ol_num);
            age = itemView.findViewById(R.id.tv_age);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != listener)
                        listener.onItemClick(MineViewHolderHeader.this);
                }
            });

            portrait.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (portraitClickListener!=null){
                        portraitClickListener.onPortraitClick();
                    }

                }
            });

        }

        @Override
        public void bindData(MineData data) {

            portrait.setImageResource(data.getPortrait());
            name.setText(data.getName());
            sex.setImageResource(data.getSex());
            olNum.setText(data.getOlNum());
            age.setText(String.valueOf(data.getAge()));

        }
    }


    //普通
    class MineViewHolderNormal extends BaseViewHolder<MineData> {

        private ImageView icon;
        private TextView txt;


        public MineViewHolderNormal(ViewGroup parent, int layoutId, final OnItemClickListener listener) {
            super(parent, layoutId, listener);

            icon = itemView.findViewById(R.id.iv_icon);
            txt = itemView.findViewById(R.id.tv_txt);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != listener)
                        listener.onItemClick(MineViewHolderNormal.this);
                }
            });

        }

        @Override
        public void bindData(MineData data) {

            icon.setImageResource(data.getIcon());
            txt.setText(itemView.getResources().getString(data.getTxt()));

        }
    }


    public interface onPortraitClickListener{

        void onPortraitClick();

    }

}
