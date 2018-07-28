package com.zkhz.a3rdlibsdemo.tab;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wk on 2018/7/26 0026
 */
public class SpringEveFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    List<String> titleList;


    public SpringEveFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.fragmentList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
