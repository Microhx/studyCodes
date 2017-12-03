package com.zhongwu.mydome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void enterWaterImage(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void enterTabLayout(View view) {
        startActivity(new Intent(this, TabLayoutActivity.class));
    }
}
