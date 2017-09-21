package com.micro.constraintlayoutdome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GoneMarginActivity extends AppCompatActivity {

    View xview ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gone_margin);

        xview = findViewById(R.id.id_tv1);
    }

    public void btn(View view) {
        if(xview.getVisibility() == View.VISIBLE) {
            xview.setVisibility(View.GONE);
        }else{
            xview.setVisibility(View.VISIBLE);
        }
    }
}
