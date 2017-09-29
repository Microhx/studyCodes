package com.micro.testgreengao.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.testgreengao.R;
import com.micro.testgreengao.greendao.gen.DaoMaster;
import com.micro.testgreengao.greendao.gen.DaoSession;
import com.micro.testgreengao.update.MySQLiteOpenHelper;
import com.micro.testgreengao.utils.LogUtils;

public class UpdateDataBaseActivity extends AppCompatActivity {

    TextView mTvInfo;

    StringBuilder sb = new StringBuilder();

    {
        sb.append("1.打开User对象中location属性\n");
        sb.append("2.编译module\n");
        sb.append("3.修改build.grade中greendao中数据库版本\n");
        sb.append("4.按下面的按钮，你的User表结构就被修改掉了\n");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_base);

        mTvInfo = (TextView) findViewById(R.id.id_tv);
        mTvInfo.setText(sb.toString());
    }

    public void updateDataBase(View view) {

        updateDB2Version2();

    }
    
    private void updateDB2Version2(){
        MySQLiteOpenHelper helper = new MySQLiteOpenHelper(this,"micro_text.db");
        DaoMaster mDaoMaster = new DaoMaster(helper.getWritableDb());
        DaoSession mDaoSession = mDaoMaster.newSession();

        Toast.makeText(this, "update db finished...", Toast.LENGTH_SHORT).show();
    }


}
