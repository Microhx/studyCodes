package com.micro.testgreengao.utils;

import android.content.Context;

import com.micro.testgreengao.bean.User;
import com.micro.testgreengao.greendao.gen.DaoMaster;
import com.micro.testgreengao.greendao.gen.DaoSession;
import com.micro.testgreengao.greendao.gen.UserDao;

import java.util.List;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/25 - 10:24 <p>
 * interface :
 */

public class DBHelper {

    private static final String DB_NAME = "micro_text.db";

    private DaoMaster mDaoMaster;

    private DaoSession mDaoSession ;

    private static volatile  DBHelper INSTANCE ;

    private DBHelper(Context ctx){
        if(null == INSTANCE) {
                    DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(ctx,DB_NAME);
                    mDaoMaster = new DaoMaster(devOpenHelper.getWritableDb());
                    mDaoSession = mDaoMaster.newSession();
        }

        /**
         * for update code
         *
         * MySQLiteOpenHelper helper = new MySQLiteOpenHelper(Context context,"db.name",null);
         * mDaoMaster = new DaoMaster(helper.getWritableDb());
         * mDaoSession = mDaoMaster.newSession();
         *
         */
    }

    public static DBHelper getInstance(Context context) {
        if(null == INSTANCE) {
            synchronized (DBHelper.class) {
                if(null == INSTANCE) {
                    INSTANCE = new DBHelper(context);
                }
            }
        }
        return INSTANCE;
    }


    public void addData(User user) {
        UserDao userDao = mDaoSession.getUserDao();
        long insertId = userDao.insert(user);
        LogUtils.d("insertId:"+insertId);

    }

    public void deleteUser(long _id) {
        UserDao userDao = mDaoSession.getUserDao();
        userDao.deleteByKey(_id);
        LogUtils.d("delete User finished");

    }

    public void deleteUser2() {
        UserDao userDao = mDaoSession.getUserDao();
        userDao.deleteAll();
        //userDao.deleteByKey();
        //userDao.deleteByKeyInTx();
        //userDao.deleteInTx();
    }



    public void updateUser(User user) {
        UserDao userDao = mDaoSession.getUserDao();
        userDao.update(user);
        LogUtils.d("update user finished");
    }

    public List<User> getAllUser() {
        UserDao userDao = mDaoSession.getUserDao();
        return  userDao.loadAll();
    }

    public User findUserById(long _id){
        UserDao userDao = mDaoSession.getUserDao();
        return userDao.load(_id);
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }

}
