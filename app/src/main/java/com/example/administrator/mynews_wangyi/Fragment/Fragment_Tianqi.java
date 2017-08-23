package com.example.administrator.mynews_wangyi.Fragment;


import android.app.Instrumentation;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mynews_wangyi.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Tianqi extends Fragment implements View.OnClickListener {

    private String pathTianqi="http://www.weather.com.cn/weather/101010100.shtml";
    public Fragment_Tianqi() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflate = inflater.inflate(R.layout.fragment_fragment__tianqi, container, false);
        final TextView tvWeaTher = inflate.findViewById(R.id.tvWeaTher);
        tvWeaTher.setOnClickListener(this);
        new Thread(){
            @Override
            public void run() {

                try {
                    Document document = Jsoup.connect(pathTianqi).get();
                    final String  attr= document.select("div.c7d").select("input").get(0).attr("value");

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvWeaTher.setText(attr);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        return inflate;
    }

    @Override
    public void onClick(View view) {
        //回退键
        new Thread() {
            public void run() {
                Instrumentation inst = new Instrumentation();
                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);
            }
        }.start();
    }
}
