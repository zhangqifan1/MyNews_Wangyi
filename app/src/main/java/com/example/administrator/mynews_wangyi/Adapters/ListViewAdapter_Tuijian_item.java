package com.example.administrator.mynews_wangyi.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mynews_wangyi.GlideImageLoader;
import com.example.administrator.mynews_wangyi.ImageActivity;
import com.example.administrator.mynews_wangyi.R;
import com.example.administrator.mynews_wangyi.Utils.ImageLoaderUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;

import JavaBeans.Bean;

/**
 * Created by 张祺钒
 * on2017/8/9.
 */

public class ListViewAdapter_Tuijian_item extends BaseAdapter {
    private Bean bean;
    private Context context;
    private static final int Type0 = 0;
    private static final int Type1 = 1;
    private static final int Type2 = 2;
    private static final int Type3 = 3;
    private static final int Type4 = 4;
    private static final int Type5 = 5;
    private static final int Type6 = 6;
    private static final int Type7 = 7;
    private static final int Type8 = 8;
    private static final int Type9 = 9;
    private Bean.ResultBean.DataBean dataBean;

    public ListViewAdapter_Tuijian_item(Bean bean, Context context) {
        this.bean = bean;
        this.context = context;
    }

    @Override
    public int getCount() {
        return bean.result.data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 10;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return Type0;
        }else if(position==1){
            return Type1;
        }else if(position==2){
            return Type2;
        }else if(position==3){
            return Type3;
        }else if(position==4){
            return Type4;
        }else if(position==5){
            return Type4;
        }else if(position==6){
            return Type4;
        }else if(position==7){
            return Type7;
        }else{
            return Type4;
        }
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        dataBean = bean.result.data.get(i);
        int type = getItemViewType(i);
        final ArrayList<String> list1= new ArrayList<String>();
        for (int j = 0; j < bean.result.data.size(); j++) {
            list1.add(bean.result.data.get(j).thumbnail_pic_s);
        }

        if(view==null){
            switch(type){
                case Type0:
                    holder=new ViewHolder();
                    view=view.inflate(context, R.layout.item_tuijian_0,null);
                    holder.banner= view.findViewById(R.id.banner);
                    view.setTag(holder);
                    break;
                case Type1:
                    holder=new ViewHolder();
                    view=view.inflate(context, R.layout.item_tuijian_1,null);
                    holder.textView=view.findViewById(R.id.tuijian_item1_tv);
                    view.setTag(holder);
                    break;
                case Type2:
                    holder=new ViewHolder();
                    view=view.inflate(context, R.layout.item_tuijian_2,null);
                    holder.textView=view.findViewById(R.id.tuijian_item2_tv);
                    view.setTag(holder);
                    break;
                case Type3:
                    holder=new ViewHolder();
                    view=view.inflate(context, R.layout.item_tuijian_3,null);
                    holder.textView=view.findViewById(R.id.tuijian_item3_tv);
                    view.setTag(holder);
                    break;
                case Type4:
                    holder=new ViewHolder();
                    view=view.inflate(context, R.layout.item_tuijian_4,null);
                    holder.imageView=view.findViewById(R.id.tuijian_item4_image);
                    holder.textView=view.findViewById(R.id.tuijian_item4_tv);
                    holder.textView1=view.findViewById(R.id.tuijian_item4_tvFol);
                    view.setTag(holder);
                    break;
                case Type7:
                    holder=new ViewHolder();
                    view=view.inflate(context, R.layout.item_tuijian_7,null);
                    holder.textView=view.findViewById(R.id.tuijian_item7_tv);
                    view.setTag(holder);
                    break;
                default:
                    break;
            }
        }else {
            holder= (ViewHolder) view.getTag();
        }
            switch(type){
                case Type0:
                    final ArrayList<String> list=new ArrayList<>();
                    list.add(dataBean.thumbnail_pic_s02);
                    list.add(dataBean.thumbnail_pic_s03);
                    holder.banner.setImageLoader(new GlideImageLoader())
                            .setImages(list).start();
                    holder.banner.setOnBannerClickListener(new OnBannerClickListener() {
                        @Override
                        public void OnBannerClick(int position) {
                            System.out.println("111111111111111111"+list.size());
                            Intent intent = new Intent(context, ImageActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putStringArrayList("name", list);
                            intent.putExtras(bundle);
                            intent.putExtra("shu",i);
                            context.startActivity(intent);
                        }
                    });
                    break;
                case Type1:
                    holder= (ViewHolder) view.getTag();
                    holder.textView.setText(dataBean.date+"");
                    break;
                case Type2:
                    holder= (ViewHolder) view.getTag();
                    holder.textView.setText(dataBean.title+"");
                    break;
                case Type3:
                    holder= (ViewHolder) view.getTag();
                    holder.textView.setText(dataBean.author_name);
                    break;
                case Type4:
                    holder= (ViewHolder) view.getTag();
                    ImageLoaderUtils.setImageView(dataBean.thumbnail_pic_s,context,holder.imageView);
                    holder.imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("111111111111111111"+list1.size());
                            Intent intent = new Intent(context, ImageActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putStringArrayList("name",list1);
                            intent.putExtra("shu",i);
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                        }
                    });

                    holder.textView.setText(dataBean.title);
                    holder.textView1.setText(dataBean.date+"");
                case Type7:
                    holder= (ViewHolder) view.getTag();
                    break;
                default:
                    break;
        }
        return view;
    }
    static class ViewHolder{
        ImageView imageView;
        ImageView imageView1;
        ImageView imageView2;
        TextView textView;
        TextView textView1;
        Banner banner;
    }
}
