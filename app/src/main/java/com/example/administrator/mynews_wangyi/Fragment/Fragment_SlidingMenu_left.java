package com.example.administrator.mynews_wangyi.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.mynews_wangyi.Adapters.ListViewAdapter_LeftFragment;
import com.example.administrator.mynews_wangyi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_SlidingMenu_left extends Fragment {

    private View inflate;
    private FragmentTuijian tuijian;
    private Fragment_Dingyue dingyue;
    private FragmentManager manager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_fragment__sliding_menu_left, container, false);
        ListView lv = inflate.findViewById(R.id.fragment_left_listview);
        ListViewAdapter_LeftFragment adapter_leftFragment = new ListViewAdapter_LeftFragment(getContext());

        lv.setAdapter(adapter_leftFragment);
        tuijian = new FragmentTuijian();
        dingyue = new Fragment_Dingyue();
        manager = getActivity().getSupportFragmentManager();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getActivity(), "第" + i + "个条目", Toast.LENGTH_SHORT).show();

                switch(i){
                    case 0:
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.setCustomAnimations(R.anim.set, R.anim.setout);
                        transaction.remove(dingyue).commit();
                        break;
                    case 1:
                        FragmentTransaction transaction1 = manager.beginTransaction();
                        transaction1.setCustomAnimations(R.anim.set, R.anim.setout);
                        transaction1.replace(R.id.rela,dingyue).show(dingyue).commit();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    default:
                        break;
                }

            }
        });
        return inflate;
    }


}

