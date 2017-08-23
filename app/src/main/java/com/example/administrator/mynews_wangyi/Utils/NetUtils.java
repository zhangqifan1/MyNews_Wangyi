package com.example.administrator.mynews_wangyi.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 张祺钒
 * on2017/8/17.
 */

public class NetUtils {
    /**
     * 用户手机是否有网络,判断的工具类
     */

    //判断网络是否连接
    public static boolean isNetWorkAvailable(Context context) {
        //网络连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //网络信息
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null) {
            return true;
        }

        return false;
    }

    //判断是否是wifi
    public static boolean isWifi(Context context) {
        //网络连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //网络信息
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.getType() == connectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }


    //判断是否是手机流量
    public static boolean isMobile(Context context) {
        //网络连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //网络信息
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.getType() == connectivityManager.TYPE_MOBILE) {
            return true;
        }
        return false;
    }


}