package com.example.administrator.mynews_wangyi.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.administrator.mynews_wangyi.R;

/**
 * Created by 张祺钒
 * on2017/8/16.
 */

public class ListViewAdapter_LeftFragment extends BaseAdapter {
    private Context context;

    public ListViewAdapter_LeftFragment(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 5;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            view=view.inflate(context, R.layout.item_left_fragment,null);
            holder.imageView=view.findViewById(R.id.item_left_image);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        if(i==0){
            holder.imageView.setImageResource(R.drawable.xinwen);
        }
        if(i==1){
            holder.imageView.setImageResource(R.drawable.dingyue);
        }
        if(i==2){
            holder.imageView.setImageResource(R.drawable.tupian);
        }
        if(i==3){
            holder.imageView.setImageResource(R.drawable.shipin);
        }
        if(i==4){
            holder.imageView.setImageResource(R.drawable.genti);
        }
        return view;
    }
    static class ViewHolder{
        ImageView imageView;
    }

}
