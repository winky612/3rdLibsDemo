package com.zkhz.a3rdlibsdemo.recyclerview.animal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zkhz.a3rdlibsdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/12 0012.
 */

public class AnimalRVActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    private List<Animal> list;
    private AnimalRVAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        ButterKnife.bind(this);

        addData();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AnimalRVActivity.this, LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(linearLayoutManager);
        adapter=new AnimalRVAdapter();

        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mShouldScroll && RecyclerView.SCROLL_STATE_IDLE == newState) {
                    mShouldScroll = false;
                    smoothMoveToPosition(rv, mToPosition);
                }
            }
        });

        adapter.setOnItemClickListener(new AnimalRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder) {

                int pos = holder.getLayoutPosition();
                if (pos != -1) {
                    smoothMoveToPosition(rv, pos);
                } else {
                    smoothMoveToPosition(rv, pos + 1);
                }
            }
        });


        adapter.setList(list);
        rv.setAdapter(adapter);


    }

    private void addData() {
        list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(new Animal("CAT"));
            list.add(new Animal("狗狗"));
            list.add(new Animal("虫虫"));
        }
    }


    //目标项是否在最后一个可见项之后
    private boolean mShouldScroll;
    //记录目标项位置
    private int mToPosition;

    /**
     * 滑动到指定位置
     */
    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (position < firstItem) {
            // 第一种可能:跳转位置在第一个可见位置之前
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 第二种可能:跳转位置在第一个可见位置之后
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            // 第三种可能:跳转位置在最后可见项之后
            mRecyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mShouldScroll = true;
        }
    }



}
