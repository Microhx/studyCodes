package com.micro.testgreengao.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.testgreengao.R;
import com.micro.testgreengao.bean.one2many2.Company;
import com.micro.testgreengao.bean.one2many2.SuccessfulMan;
import com.micro.testgreengao.greendao.gen.DaoSession;
import com.micro.testgreengao.utils.DBHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Many2Many2Activity extends AppCompatActivity {

    TextView mTvInfo;

    DaoSession mDaoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one2_many3);

        mTvInfo = (TextView) findViewById(R.id.id_tv_info);
        mDaoSession = DBHelper.getInstance(this).getDaoSession();
    }

    public void addBtn(View view) {
        SuccessfulMan man = new SuccessfulMan(null, "micro", 34);
        mDaoSession.getSuccessfulManDao().insert(man);

        Company company = new Company(null, "micro", "baidu1", new Date(), 2000);
        Company company2 = new Company(null, "micro", "tencent1", new Date(), 4000);
        Company company3 = new Company(null, "micro", "ali1", new Date(), 5000);

        mDaoSession.getCompanyDao().insert(company);
        mDaoSession.getCompanyDao().insert(company2);
        mDaoSession.getCompanyDao().insert(company3);


        Toast.makeText(this, "insert data finished....", Toast.LENGTH_SHORT).show();
    }

    public void loadData(View view) {
        SuccessfulMan success = mDaoSession.getSuccessfulManDao().load(1L);
        if (null != success) {
            List<Company> listCompany = success.getCompanyList();

            mTvInfo.setText(success.toString() + "\n" + listCompany);

        } else {
            Toast.makeText(this, "load data null", Toast.LENGTH_SHORT).show();
        }

    }
}
