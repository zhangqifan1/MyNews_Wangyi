package com.example.administrator.mynews_wangyi.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mynews_wangyi.Utils.ImageLoaderUtils;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by 张祺钒
 * on2017/8/18.
 */

public class ViewPagerAdapter extends PagerAdapter {


    private ArrayList<String> list;
    private Context context;
    public ViewPagerAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        PhotoView view=new PhotoView(context);
        ImageLoaderUtils.setImageView(list.get(position),context,view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
