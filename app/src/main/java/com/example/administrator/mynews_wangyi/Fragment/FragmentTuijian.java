package com.example.administrator.mynews_wangyi.Fragment;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.bawei.xlistviewlibrary.XListView;
import com.example.administrator.mynews_wangyi.Adapters.ListViewAdapter_Tuijian_item;
import com.example.administrator.mynews_wangyi.R;
import com.example.administrator.mynews_wangyi.Utils.Tools;
import com.example.administrator.mynews_wangyi.WebViewActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import JavaBeans.Bean;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTuijian extends Fragment implements XListView.IXListViewListener, AdapterView.OnItemClickListener {
    private String path = "http://v.juhe.cn/toutiao/index?type=top&key=dbedecbcd1899c9785b95cc2d17131c5";
    private String path2 = "http://result.eolinker.com/k2BaduF2a6caa275f395919a66ab1dfe4b584cc60685573?uri=gj";
    private XListView lv;
    private ListViewAdapter_Tuijian_item adapter;
    private Bean bean2;
    private Bean bean;

    private List<Bean.ResultBean.DataBean> beanList = null;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();
            closeXlistView();
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_fragment__tuijian, container, false);
        lv = (XListView) inflate.findViewById(R.id.lv_tuijian);

        new MyAsyncTask().execute();
        lv.setXListViewListener(this);
        lv.setOnItemClickListener(this);
        return inflate;
    }

    private int a = 0;

    @Override
    public void onRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                a++;
                new Thread() {
                    @Override
                    public void run() {

                        try {
                            HttpURLConnection connection = (HttpURLConnection) new URL(path2).openConnection();
                            connection.setRequestMethod("GET");
                            connection.setConnectTimeout(6000);
                            connection.setReadTimeout(6000);
                            int code = connection.getResponseCode();
                            if (code == 200) {
                                InputStream inputStream = connection.getInputStream();
                                String textFromStream = Tools.getTextFromStream(inputStream);
                                Gson gson = new Gson();
                                bean2 = gson.fromJson(textFromStream, Bean.class);
                                List<Bean.ResultBean.DataBean> dataBeanList = bean2.result.data;
                                bean.result.data.add(0, dataBeanList.get(a));
                                handler.sendEmptyMessage(0);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {

    }

    private void closeXlistView() {
        lv.stopRefresh();
        lv.setRefreshTime(getCurrentTime());
    }

    private String getCurrentTime() {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(date);
        return time;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//        ArrayList<String> arrayList=new ArrayList<>();
        List<Bean.ResultBean.DataBean> data = bean.result.data;
        Intent intent = new Intent(getActivity(), WebViewActivity.class);
        intent.putExtra("name", data.get(i).url);
        startActivity(intent);
    }

    //自定义的AsyncTask
    private class MyAsyncTask extends AsyncTask<Void, Integer, String> {

        private String textFromStream;

        //这个方法运行在主线程,在doInBackground之前运行,我们一般做初始化
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //这个方法运行在子线程,我们可以做一个耗时操作
        @Override
        protected String doInBackground(Void... voids) {
            ////////////////////////////////网络请求的操作,注意用return返回获取到的字符串,加网络权限////////////////////////////
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(path).openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(6000);
                connection.setReadTimeout(6000);
                int code = connection.getResponseCode();
                if (code == 200) {
                    InputStream inputStream = connection.getInputStream();
                    textFromStream = Tools.getTextFromStream(inputStream);
                }
                return textFromStream;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        //运行在主线程,这个方法在doInBackground执行之后执行.我们一般做从网络拿到数据,使用的数据的操作
        @Override
        protected void onPostExecute(String s) {
            ////////////////////拿到处理后的数据,更新UI///////////////////////////////
            super.onPostExecute(s);
            Gson gson = new Gson();
            bean = gson.fromJson(s, Bean.class);
            getActivity().runOnUiThread(new Runnable() {

                                            @Override
                                            public void run() {
                                                System.out.println("00000000000" + bean.result.data.size());
                                                adapter = new ListViewAdapter_Tuijian_item(bean, getContext());
                                                lv.setPullLoadEnable(true);
                                                lv.setPullRefreshEnable(true);
                                                lv.setAdapter(adapter);

                                            }
                                        }
            );
        }
    }


}



