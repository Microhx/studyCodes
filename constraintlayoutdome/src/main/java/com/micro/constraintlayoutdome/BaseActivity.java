package com.micro.constraintlayoutdome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base2);
    }

    /**
     * 基本实现
     * @param view
     */
    public void btn1(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    /**
     * 宽和高 存在比例
     * @param view
     */
    public void btn2(View view) {
        startActivity(new Intent(this,RatioActivity.class));
    }


    /**
     * goneMargin模式
     * @param view
     */
    public void btn3(View view) {
        startActivity(new Intent(this,GoneMarginActivity.class));
    }

    public void btn4(View view) {
        startActivity(new Intent(this,MultiChainsActivity.class));
    }
}
