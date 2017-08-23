package com.example.administrator.mynews_wangyi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            startActivity(new Intent(GuideActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.set,R.anim.setout);
//            Toast.makeText(GuideActivity.this,"俺老孙来也!biubiubiu.咚咚咚.",Toast.LENGTH_SHORT).show();
        }
    };
    private ImageView mTiaoImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //判断第几次登录
        getData();

        initView();
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    private void getData() {
        SharedPreferences sp = getSharedPreferences("content", MODE_PRIVATE);
        boolean flag = sp.getBoolean("flag", false);
        if(flag==false){
           //第一次进
            sp.edit().putBoolean("flag",true).commit();
        }
        if(flag==true){
//            handler.sendEmptyMessage(0);
        }
    }

    private void initView() {
        mTiaoImage = (ImageView) findViewById(R.id.tiaoImage);
        mTiaoImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tiaoImage:
                handler.sendEmptyMessage(0);
                break;
        }
    }

}
