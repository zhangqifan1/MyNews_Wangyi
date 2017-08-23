package com.example.administrator.mynews_wangyi.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mynews_wangyi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张祺钒
 * on2017/8/20.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    public MyAdapter(Context context) {
        this.context = context;
    }

    private Context context;
    private List<String> list=new ArrayList<>();

    public void setList(List<String> list) {
        this.list = list;
    }
    public interface MyItemOnClickListener{
        void onClick(View v, int position);
    }
    public MyItemOnClickListener myItemOnClickListener;

    public void setMyItemOnClickListener(MyItemOnClickListener myItemOnClickListener) {
        this.myItemOnClickListener = myItemOnClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //在创建方法里  填充XML  得到View
        View view = View.inflate(context, R.layout.itemrecycle, null);
        //得到VieWHolder
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用我们自己的方法
                myItemOnClickListener.onClick(view, (Integer) view.getTag());
            }
        });

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.itemView.setTag(i);
        myViewHolder.tv.setText(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
