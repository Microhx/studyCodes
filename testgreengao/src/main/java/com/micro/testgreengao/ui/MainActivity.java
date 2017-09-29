package com.micro.testgreengao.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.testgreengao.R;
import com.micro.testgreengao.bean.User;
import com.micro.testgreengao.utils.DBHelper;
import com.micro.testgreengao.utils.LogUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText mEtName;
    EditText mETAge;
    TextView mTvInfo;

    DBHelper mDBHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtName = (EditText) findViewById(R.id.id_et_name);
        mETAge = (EditText) findViewById(R.id.id_et_age);
        mTvInfo = (TextView) findViewById(R.id.id_tv_info);

        mDBHelper = DBHelper.getInstance(this);
    }

    public void btnAdd(View view) {
        String name = mEtName.getText().toString().trim();
        int age = Integer.parseInt(mETAge.getText().toString().trim());
        User user = new User(null,name,age);

        mDBHelper.addData(user);

        mEtName.setText("");
        mETAge.setText("");
    }

    public void btnLoadAll(View view) {
        List<User> list = mDBHelper.getAllUser();
        StringBuilder sb = new StringBuilder();

        for(User u : list) {
            sb.append(u.toString());
            sb.append("\n");
        }

        LogUtils.d("allUserMessage:" + sb);
        
        mTvInfo.setText(sb.toString());
    }

    /**
     * 更新信息
     * @param view
     */
    public void btnUpdate(View view) {
        User user = new User(1L,"李四",20);
        mDBHelper.updateUser(user);
        Toast.makeText(this, "update success", Toast.LENGTH_SHORT).show();
    }

    public void btnDelete(View view) {
        mDBHelper.deleteUser(1L);

        Toast.makeText(this, "delete success", Toast.LENGTH_SHORT).show();
    }

}
