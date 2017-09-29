package com.micro.testgreengao.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.micro.testgreengao.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    public void CRUD(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void query(View view) {
        startActivity(new Intent(this,QueryActivity.class));
    }

    public void multiAnnotation(View view) {
        startActivity(new Intent(this,MultiAnnotationActivity.class));
    }

    public void toOneModel(View view) {
        startActivity(new Intent(this,ToOneActivity.class));
    }

    public void toManyModel2(View view) {
        startActivity(new Intent(this,Many2Many1Activity.class));
    }

    public void toManyModel3(View view) {
        startActivity(new Intent(this,Many2Many2Activity.class));
    }

    public void toManyModel4(View view) {
        startActivity(new Intent(this,Many2Many3Activity.class));
    }

    public void updateDb(View view) {
        startActivity(new Intent(this,UpdateDataBaseActivity.class));
    }
}
