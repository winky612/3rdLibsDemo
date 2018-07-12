package com.zkhz.a3rdlibsdemo.recyclerview.setting;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zkhz.a3rdlibsdemo.R;
import com.zkhz.a3rdlibsdemo.recyclerview.base.BaseRVAdapter;
import com.zkhz.a3rdlibsdemo.recyclerview.base.OnItemClickListener;

/**
 * Created by wk on 2018/7/12.
 */

public class SettingAdapter extends BaseRVAdapter<SettingData> {


    @Override
    public BaseViewHolder<SettingData> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SettingViewHolder(parent, R.layout.item_setting, listener);

//        方法1
//        if (viewType==0){
//            return new SettingViewHolder(parent,R.layout.item_setting,listener)
//        }else {
//            return 另一个holder
//        }
//
    }

//    @Override
//    public int getItemViewType(int position) {
//        return getList().get(position).getTitle().equals(R.string.clear_cache)?1:0;
//    }

    class SettingViewHolder extends BaseViewHolder<SettingData> {

        private ImageView imageView;
        private TextView title;
        private TextView tvCache;
        private ImageView arrow;

        public SettingViewHolder(ViewGroup parent, int layoutId, final OnItemClickListener listener) {
            super(parent, layoutId, listener);

            imageView = itemView.findViewById(R.id.iv_about_us);
            title = itemView.findViewById(R.id.tv_about_us);
            tvCache = itemView.findViewById(R.id.tv_cache);
            arrow = itemView.findViewById(R.id.arrow_right);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != listener)
                        listener.onItemClick(SettingViewHolder.this);
                }
            });
        }

        @Override
        public void bindData(SettingData data) {

            imageView.setImageResource(data.getIcon());
            title.setText(itemView.getResources().getString(data.getTitle()));
            boolean ifCache = data.getTitle() == R.string.clear_cache;
            if (ifCache) {
                String cacheTxt =  TextUtils.isEmpty(data.getCache())?"0k":data.getCache();
                tvCache.setText(cacheTxt);
                tvCache.setVisibility(View.VISIBLE);
                arrow.setVisibility(View.INVISIBLE);

            } else {
                tvCache.setVisibility(View.GONE);

            }
        }


    }
}
