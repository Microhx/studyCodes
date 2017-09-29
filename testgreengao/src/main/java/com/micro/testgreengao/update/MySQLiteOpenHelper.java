package com.micro.testgreengao.update;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

import com.micro.testgreengao.bean.Note;
import com.micro.testgreengao.bean.User;
import com.micro.testgreengao.greendao.gen.DaoMaster;
import com.micro.testgreengao.greendao.gen.NoteDao;
import com.micro.testgreengao.greendao.gen.UserDao;
import com.micro.testgreengao.utils.LogUtils;

import org.greenrobot.greendao.database.StandardDatabase;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/25 - 18:13 <p>
 * interface :
 */

public class MySQLiteOpenHelper extends DaoMaster.OpenHelper {

    public MySQLiteOpenHelper(Context context, String name) {
        this(context, name,null);
    }


    MySQLiteOpenHelper(Context context,String name , CursorFactory factory) {
        super(context,name,factory);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);

        LogUtils.d("update oldVersion : " +oldVersion + " , newVersion : " + newVersion);

       //to update
       MigrationHelper.getInstance().migrate(new StandardDatabase(db), UserDao.class);
    }
}
