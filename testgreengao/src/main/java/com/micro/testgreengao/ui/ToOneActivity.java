package com.micro.testgreengao.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.testgreengao.R;
import com.micro.testgreengao.bean.one2one.IdCard;
import com.micro.testgreengao.bean.one2one.Person;
import com.micro.testgreengao.utils.DBHelper;
import com.micro.testgreengao.utils.LogUtils;

public class ToOneActivity extends AppCompatActivity {

    TextView mtvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_one);
        mtvInfo = (TextView) findViewById(R.id.id_tv_info);
    }


    public void addPersonAndIdCard(View view) {
        IdCard idCard = new IdCard(null,"421111111133","shanghai22");
        DBHelper.getInstance(this).getDaoSession().getIdCardDao().insert(idCard);

        Person p = new Person(null,"lisi",null);
        p.setIdCard(idCard);
        Long insertId = DBHelper.getInstance(this).getDaoSession().getPersonDao().insert(p);

        Toast.makeText(this, "the insert id is " + insertId, Toast.LENGTH_SHORT).show();
    }

    public void updateData(View view) {
        IdCard idCard = new IdCard(null,"421111111144","shanghai44");
        DBHelper.getInstance(this).getDaoSession().getIdCardDao().insert(idCard);

        Person p = DBHelper.getInstance(this).getDaoSession().getPersonDao().load(2L);

        p.setIdCard(idCard);

        DBHelper.getInstance(this).getDaoSession().getPersonDao().update(p);

        Toast.makeText(this, "update data finished....", Toast.LENGTH_SHORT).show();
    }


    public void loadData(View view) {
        Person p = DBHelper.getInstance(this).getDaoSession().getPersonDao().load(2L);
        if(null != p) {
            mtvInfo.setText(p.toString());
        }else {
            Toast.makeText(this, "data is null--->>", Toast.LENGTH_SHORT).show();
        }

    }

}
