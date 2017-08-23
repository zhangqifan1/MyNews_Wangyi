package com.example.administrator.mynews_wangyi.Fragment;

import android.app.Instrumentation;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mynews_wangyi.Adapters.MyAdapter;
import com.example.administrator.mynews_wangyi.MainActivity;
import com.example.administrator.mynews_wangyi.R;

import java.util.ArrayList;
import java.util.List;

import JavaBeans.BeanTitle;

/**
 * Created by 张祺钒
 * on2017/8/18.
 */
public class Fragment_Pindao extends Fragment implements View.OnClickListener {
    private View view;
    /**
     * N个未读栏目点击进入
     */
    private TextView mTvNumbers;
    private ImageView mImageHide;
    private RecyclerView mRecycleTop;
    private RecyclerView mRecycleBot;
    //存放上面的mRecycleTop的数据
    private List<String> listTop=new ArrayList<>();
    //存放下面的mRecycleBot的数据
    private List<String> listBot=new ArrayList<>();
    private MyAdapter myAdapterBot;
    private MyAdapter myAdapterUp;
    private BeanTitle bean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bean = MainActivity.bean;
        View inflate = inflater.inflate(R.layout.frag_pindao, container, false);
        initView(inflate);
        //找控件
        initView(inflate);
        //准备数据
        preparData();
        //初始化上面的
        initUp();
        //初始化下面的
        initBot();
        return inflate;
    }

    private void initView(View inflate) {

        mTvNumbers = (TextView) inflate.findViewById(R.id.tvNumbers);
        mImageHide = (ImageView) inflate.findViewById(R.id.image_hide);
        mImageHide.setOnClickListener(this);
        mRecycleTop = (RecyclerView) inflate.findViewById(R.id.recycleTop);
        mRecycleBot = (RecyclerView) inflate.findViewById(R.id.recycleBot);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //让Button有返回键效果
            case R.id.image_hide:
                new Thread() {
                    public void run() {
                        Instrumentation inst = new Instrumentation();
                        inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
                    }
                }.start();
                break;
        }
    }
    private void initBot() {
        //得到Adapter
        myAdapterBot = new MyAdapter(getContext());
        myAdapterBot.setList(listBot);
        //适配数据
        mRecycleBot.setAdapter(myAdapterBot);
        //设置行数
        GridLayoutManager gridLayoutManagerUp = new GridLayoutManager(getContext(), 3);
        mRecycleBot.setLayoutManager(gridLayoutManagerUp);
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.set);
        mRecycleBot.setAnimation(animation);
        myAdapterBot.setMyItemOnClickListener(new MyAdapter.MyItemOnClickListener() {
            @Override
            public void onClick(View v, int position) {
                //下面的RecyclerView添加当前点击的条目信息并刷新数组
                listTop.add(listBot.get(position));
//                myAdapterUp.setList(listTop);
                myAdapterUp.notifyDataSetChanged();
                //从当前数组移除数据并刷新数组
                listBot.remove(position);
//                myAdapterBot.setList(listBot);
                myAdapterBot.notifyDataSetChanged();
            }
        });


    }
    private void initUp() {
        myAdapterUp = new MyAdapter(getContext());
        myAdapterUp.setList(listTop);
        //初始化
        //RecyclerView显示样式
        mRecycleTop.setAdapter(myAdapterUp);
        //设置行数
        GridLayoutManager gridLayoutManagerUp = new GridLayoutManager(getContext(), 3);
        mRecycleTop.setLayoutManager(gridLayoutManagerUp);
        myAdapterUp.setMyItemOnClickListener(new MyAdapter.MyItemOnClickListener() {
            @Override
            public void onClick(View v, int position) {
                //下面的RecyclerView添加当前点击的条目信息并刷新数组
                listBot.add(listTop.get(position));
//                myAdapterBot.setList(listBot);
                myAdapterBot.notifyDataSetChanged();
                //从当前数组移除数据并刷新数组
                listTop.remove(position);
//                myAdapterUp.setList(listTop);
                myAdapterUp.notifyDataSetChanged();
            }
        });

    }

    private void preparData() {
        List<BeanTitle.ResultBean.DateBean> date = bean.result.date;
        for (int i = 0; i < date.size(); i++) {
            listTop.add(date.get(i).title);
        }
        for (int i = 0; i < 10; i++) {
            listBot.add("添加的数据"+i);
        }
    }

}
