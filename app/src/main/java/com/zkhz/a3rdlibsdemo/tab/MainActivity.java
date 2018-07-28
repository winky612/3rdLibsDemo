package com.zkhz.a3rdlibsdemo.tab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.zkhz.a3rdlibsdemo.R;
import com.zkhz.homeworld.live.LiveFragment;
import com.zkhz.homeworld.springeve.home.SpringEveFragment;
import com.zkhz.homeworld.springeve.home.SpringEveFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tl_home)
    TabLayout tlHome;
    @BindView(R.id.vp_home)
    ViewPager vpHome;

    private SpringEveFragmentPagerAdapter pagerAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();
    private String tabs[];
    private int tabIcons[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        fragmentList.add(LiveFragment.newInstance());
        fragmentList.add(SpringEveFragment.newInstance());

        tabs = new String[]{"直播", "春晚"};
        tabIcons = new int[]{R.drawable.selector_tab_home_ivlive, R.drawable.selector_tab_home_ivspring};

        pagerAdapter = new SpringEveFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        vpHome.setAdapter(pagerAdapter);
        tlHome.setupWithViewPager(vpHome);

        for (int i = 0; i < tlHome.getTabCount(); i++) {

            TabLayout.Tab tab = tlHome.getTabAt(i);
            if (tab != null) {

                tab.setCustomView(getTabView(i));
            }

            if (tab.getCustomView() != null) {
                View tabView = (View) tab.getCustomView().getParent();
                tabView.setTag(i);
            }


        }

    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#ee443d"),false);

    }

    private View getTabView(final int position) {
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.tab_custom_home, null);
        TextView title = (TextView) view.findViewById(R.id.tv_title);
        title.setText(tabs[position]);
        ImageView ivTab = (ImageView) view.findViewById(R.id.iv_tab);
        ivTab.setImageResource(tabIcons[position]);

        vpHome.setCurrentItem(0);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vpHome.setCurrentItem(position);

            }
        });

        return view;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }
}
