package com.example.administrator.mynews_wangyi.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.mynews_wangyi.R;

import java.util.List;

/**
 * Created by 张祺钒
 * on2017/8/17.
 */

public class ListViewAdapter_Search extends BaseAdapter {
    private List<String> list;
    private Context context;
    private String string;

    public ListViewAdapter_Search(List<String> list, Context context, String string) {
        this.list = list;
        this.context = context;
        this.string = string;
    }

    @Override
    public int getCount() {
        return 1;
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
            view=view.inflate(context, R.layout.item_onetext,null);
            holder.textView=view.findViewById(R.id.tvONE);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
//        holder.textView.setVisibility(View.GONE);
        holder.textView.setText(string);
//        if(list.get(i).equals(string)){
//            holder.textView.setVisibility(View.VISIBLE);
//        }
        return view;
    }
    static class ViewHolder{
        TextView textView;
    }
}
