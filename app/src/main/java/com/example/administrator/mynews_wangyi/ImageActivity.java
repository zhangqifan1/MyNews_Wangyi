package com.example.administrator.mynews_wangyi;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.mynews_wangyi.Adapters.ViewPagerAdapter;

import java.util.ArrayList;

public class ImageActivity extends AppCompatActivity {


    private ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        initView();
        Bundle name1 = getIntent().getExtras();
        ArrayList<String> name2 = name1.getStringArrayList("name");

        int a = getIntent().getIntExtra("shu",1);
        System.out.println("0000000000000000:"+a);

        ViewPagerAdapter adapter=new ViewPagerAdapter(name2,this);
        mViewpager.setAdapter(adapter);
        mViewpager.setCurrentItem(a);
    }

    private void initView() {
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
    }


}
