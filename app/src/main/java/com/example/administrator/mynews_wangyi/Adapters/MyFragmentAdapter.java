package com.example.administrator.mynews_wangyi.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张祺钒
 * on2017/8/8.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {
    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }
    private List<Fragment> list=new ArrayList<>();
    public void setFragList(List<Fragment> list1){
        this.list=list1;
    }
    @Override
    public Fragment getItem(int position)
    {

//        Fragment page = null;
//        if (list.size() > position) {
//            page = list.get(position);
//            if (page != null) {
//                return page;
//            }
//        }
//
//        while (position>=list.size()) {
//            list.add(null);
//        }
        return list.get(position);

    }

    @Override
    public int getCount() {
        return list.size();
    }
}
