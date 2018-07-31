package com.zkhz.a3rdlibsdemo.tab;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wk.banner.BannerLayout;
import com.zkhz.a3rdlibsdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wk on 2018/7/25 0025
 */
public class SpringEveFragment extends Fragment {

    Activity mActivity;
    @BindView(R.id.tv_spring_title)
    TextView tvSpringTitle;
    @BindView(R.id.banner_home)
    BannerLayout bannerHome;
    @BindView(R.id.tv_join_count)
    TextView tvJoinCount;
    @BindView(R.id.tv_view_count)
    TextView tvViewCount;
    @BindView(R.id.tv_ticket_count)
    TextView tvTicketCount;
    @BindView(R.id.tv_action_title)
    TextView tvActionTitle;
    @BindView(R.id.tv_action_content)
    TextView tvActionContent;
    @BindView(R.id.tv_action_time)
    TextView tvActionTime;
    @BindView(R.id.tv_review_back)
    TextView tvReviewBack;
    @BindView(R.id.cl_home_action)
    ConstraintLayout clHomeAction;
    @BindView(R.id.cl_home_vote)
    ConstraintLayout clHomeVote;
    @BindView(R.id.rv_all_zones)
    RecyclerView rvAllZones;
    @BindView(R.id.cl_home_all_zones)
    ConstraintLayout clHomeAllZones;
    Unbinder unbinder;
    @BindView(R.id.iv_title1)
    ImageView ivTitle1;
    @BindView(R.id.tv_join_count2)
    TextView tvJoinCount2;
    @BindView(R.id.tv_view_count2)
    TextView tvViewCount2;

    private View view;

    private SpringEveRVAdapter adapter;
    private List<SpringZoneData> list;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.mActivity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }

            unbinder = ButterKnife.bind(this, view);
            return view;
        }
        view = inflater.inflate(R.layout.fragment_spring_eve, container, false);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showData();


    }

    private void showData() {

        list = new ArrayList<>();
        list.add(new SpringZoneData("报名进行中", "北京赛区1"));
        list.add(new SpringZoneData("报名进行中", "上海赛区2"));
        list.add(new SpringZoneData("报名进行中", "上海赛区3"));
        list.add(new SpringZoneData("报名进行中", "上海赛区4"));
        list.add(new SpringZoneData("报名进行中", "上海赛区5"));
        list.add(new SpringZoneData("报名进行中", "上海赛区6"));
        list.add(new SpringZoneData("报名进行中", "上海赛区7"));
        list.add(new SpringZoneData("报名进行中", "上海赛区8"));
        list.add(new SpringZoneData("报名进行中", "上海赛区9"));
        list.add(new SpringZoneData("报名进行中", "上海赛区10"));
        list.add(new SpringZoneData("报名进行中", "上海赛区11"));

        LinearLayoutManager manager = new LinearLayoutManager(mActivity);
        rvAllZones.setLayoutManager(manager);
        rvAllZones.setNestedScrollingEnabled(false);

        setAdapter();
    }


    private void setAdapter() {

        if (adapter == null) {
            adapter = new SpringEveRVAdapter();
            adapter.setList(list);
            rvAllZones.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {

                Toast.makeText(mActivity, list.get(holder.getAdapterPosition()).getZoneName(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick(R.id.tv_review_back)
    public void onViewClicked() {


    }

    public static SpringEveFragment newInstance() {

        SpringEveFragment fragment = new SpringEveFragment();
        return fragment;
    }
}
