package com.example.administrator.mynews_wangyi.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.mynews_wangyi.R;

import JavaBeans.BeanTitle;

import static com.umeng.socialize.a.b.d.i;

/**
 * Created by 张祺钒
 * on2017/8/18.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private BeanTitle beanTitle;

    public GridViewAdapter(Context context, BeanTitle beanTitle) {
        this.context = context;
        this.beanTitle = beanTitle;
    }

    @Override
    public int getCount() {
        return beanTitle.result.date.size();
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
         public View getView(int position, View convertView, ViewGroup parent) {
             ViewHolder holder = null;
             if(convertView==null){
                 holder=new ViewHolder();
                 convertView=convertView.inflate(context, R.layout.grid_item,null);
                 holder.textView= (TextView) convertView.findViewById(R.id.tvGrid);
                 convertView.setTag(holder);
             }else{
                 holder= (ViewHolder) convertView.getTag();
             }
             holder.textView.setText(beanTitle.result.date.get(i).title);
             return convertView;
         }
         static class ViewHolder{
             TextView textView;
         }
}
