package com.example.administrator.mynews_wangyi.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.mynews_wangyi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoViewFragment extends Fragment {


    public PhotoViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_photo_view, container, false);
        return inflate;
    }

}
