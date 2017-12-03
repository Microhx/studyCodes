package com.zhongwu.mydome.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhongwu on 2017/12/3.
 */

public class CustomFragment extends Fragment {

    private String mTitle;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mTitle = getArguments().getString("title");
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        TextView tv = new TextView(getContext());
        tv.setText(mTitle);

        return tv;
    }
}
