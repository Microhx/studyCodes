package com.micro.testgreengao.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.testgreengao.R;
import com.micro.testgreengao.bean.one2many3.Book;
import com.micro.testgreengao.bean.one2many3.City;
import com.micro.testgreengao.bean.one2many3.CityAndBooks;
import com.micro.testgreengao.greendao.gen.CityAndBooksDao;
import com.micro.testgreengao.greendao.gen.DaoSession;
import com.micro.testgreengao.utils.DBHelper;

import java.util.List;

public class Many2Many3Activity extends AppCompatActivity {

    TextView mtvInfo;

    DaoSession mDaoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_many2_many3);

        mtvInfo = (TextView) findViewById(R.id.id_tv_info);

        mDaoSession = DBHelper.getInstance(this).getDaoSession();
    }

    public void addBtn(View view) {
        City city = new City(null,"shanghai");
        City city1 = new City(null,"北京");

        Book book = new Book(null,"one night",40);
        Book book1 = new Book(null,"thinking in java",68);

        mDaoSession.getCityDao().insert(city);
        mDaoSession.getCityDao().insert(city1);

        mDaoSession.getBookDao().insert(book);
        mDaoSession.getBookDao().insert(book1);

        CityAndBooks cb = new CityAndBooks(null,book.get_id(),city.get_id());
        CityAndBooks cb1 = new CityAndBooks(null,book1.get_id() , city1.get_id());
        
        mDaoSession.getCityAndBooksDao().insert(cb);
        mDaoSession.getCityAndBooksDao().insert(cb1);

        Toast.makeText(this, "insert data finished....", Toast.LENGTH_SHORT).show();
    }

    public void loadData(View view) {

        List<Book> bookList = mDaoSession.getCityDao().load(1L).getBookList();
        if(null != bookList) {
            mtvInfo.setText("bookList:" + bookList);

        }else{
            Toast.makeText(this, "load data error", Toast.LENGTH_SHORT).show();
        }
    }
}
