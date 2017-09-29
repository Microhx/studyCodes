package com.micro.testgreengao.ui;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.testgreengao.R;
import com.micro.testgreengao.bean.User;
import com.micro.testgreengao.greendao.gen.UserDao;
import com.micro.testgreengao.utils.DBHelper;
import com.micro.testgreengao.utils.LogUtils;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.util.List;

public class QueryActivity extends AppCompatActivity {

    TextView mTvInfo;

    DBHelper mDBHelper ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);

        mTvInfo = (TextView) findViewById(R.id.id_tv_info);
        mDBHelper = DBHelper.getInstance(this);
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


    public void btnGetById(View view) {
        User user = mDBHelper.getDaoSession().load(User.class,2L);
        if(null != user) {
            mTvInfo.setText(user.toString());
        }else{
            Toast.makeText(this, "查询数据失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnGetBySQL(View view) {
        String sql = "select * from " + UserDao.TABLENAME + " where _age > ? "  ;

        StringBuilder sb = new StringBuilder();
        Cursor cursor =  mDBHelper.getDaoSession().getDatabase().rawQuery(sql,new String[]{String.valueOf(23)});
        while (null != cursor && cursor.moveToNext()) {
            Long _id =  cursor.getLong(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("_name")) ;
            int age = cursor.getInt(cursor.getColumnIndex("_age"));
            sb.append("_id : " + _id + ",");
            sb.append("_name : " + name + ",");
            sb.append("_age : " + age);
            sb.append("\n");
        }

        mTvInfo.setText(sb.toString());
    }

    /**
     * 通过QueryBuilder查找
     * @param view
     */
    public void btnGetByQueryBuilder(View view) {

        QueryBuilder<User> queryBuilder = mDBHelper.getDaoSession().queryBuilder(User.class);

        WhereCondition.StringCondition  stringCondition = new WhereCondition.StringCondition("_name = 'micro'");
        WhereCondition.StringCondition stringCondition1 = new WhereCondition.StringCondition("_age > 20");

        List<User> userList = queryBuilder.where(stringCondition,stringCondition1).build().list();

        StringBuilder sb = new StringBuilder();
        for(User u : userList) {
            sb.append(u.toString());
            sb.append("\n");
        }

        mTvInfo.setText(sb.toString());
    }

    public void btnGetByQueryBuilder2(View view) {
        QueryBuilder<User> queryBuilder = mDBHelper.getDaoSession().queryBuilder(User.class);

        WhereCondition nameCondition = null ;
        WhereCondition ageCondition = null;
        Property[] properties = mDBHelper.getDaoSession().getUserDao().getProperties();

        for (Property p : properties) {
            if(p.columnName.equals("_name")) {
                nameCondition = p.eq("micro");
            }

            if(p.columnName.equals("_age")){
                ageCondition = p.gt(20);
            }
        }

        List<User> userList = queryBuilder.where(queryBuilder.or(nameCondition,ageCondition)).build().list();


        StringBuilder sb = new StringBuilder();

        for(User u : userList) {
            sb.append(u.toString());
            sb.append("\n");
        }

        mTvInfo.setText(sb.toString());

    }
}
