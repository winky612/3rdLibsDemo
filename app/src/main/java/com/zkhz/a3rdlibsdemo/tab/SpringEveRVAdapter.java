package com.zkhz.a3rdlibsdemo.tab;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zkhz.a3rdlibsdemo.R;

import butterknife.BindView;

/**
 * Created by wk on 2018/7/25 0025
 */
public class SpringEveRVAdapter extends BaseRVAdapter<SpringZoneData> {

    @NonNull
    @Override
    public BaseViewHolder<SpringZoneData> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SpringEveViewHolder(parent, R.layout.item_rv_home_all_zones, listener);
    }


    class SpringEveViewHolder extends BaseViewHolder<SpringZoneData> {

        @BindView(R.id.iv_zone)
        ImageView ivZone;
        @BindView(R.id.tv_zone_state)
        TextView tvZoneState;
        @BindView(R.id.tv_zone_name)
        TextView tvZoneName;
        @BindView(R.id.cl_zone)
        ConstraintLayout clZone;

        public SpringEveViewHolder(ViewGroup parent, int layoutId, final OnItemClickListener listener) {
            super(parent, layoutId, listener);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {

                        listener.onItemClick(SpringEveViewHolder.this);
                    }
                }
            });

        }

        @Override
        public void bindData(SpringZoneData data) {

            tvZoneState.setText(data.getState());
            tvZoneName.setText(data.getZoneName());

        }

    }

}
