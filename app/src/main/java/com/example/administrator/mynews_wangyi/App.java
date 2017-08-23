package com.example.administrator.mynews_wangyi;

import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.umeng.socialize.PlatformConfig;

import org.xutils.x;

/**
 * Created by 张祺钒
 * on2017/8/8.
 */

public class App extends Application {
    @Override
    public void onCreate() {// 将“12345678”替换成您申请的APPID，申请地址：http://open.voicecloud.cn
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=597dc5ce");
        super.onCreate();
        PlatformConfig.setQQZone("1106036236", "mjFCi0oxXZKZEWJs");
        x.Ext.init(this);

    }
}
