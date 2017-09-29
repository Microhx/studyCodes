package com.micro.testgreengao.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.micro.testgreengao.R;
import com.micro.testgreengao.bean.one2many1.Customer;
import com.micro.testgreengao.bean.one2many1.Order;
import com.micro.testgreengao.greendao.gen.CustomerDao;
import com.micro.testgreengao.greendao.gen.OrderDao;
import com.micro.testgreengao.utils.DBHelper;
import com.micro.testgreengao.utils.LogUtils;

import java.util.Date;
import java.util.List;

public class Many2Many1Activity extends AppCompatActivity {

    DBHelper mDBHelper ;

    TextView mTvInfo ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one2_many2);

        mTvInfo = (TextView) findViewById(R.id.id_tv_info);

        mDBHelper = DBHelper.getInstance(this);
    }

    public void btnAdd(View view) {
        CustomerDao customerDao = mDBHelper.getDaoSession().getCustomerDao();

        Customer customer = new Customer(null);
        customerDao.insert(customer);

        LogUtils.d("-------customerId-------->>" + customer.getId());

        OrderDao orderDao = mDBHelper.getDaoSession().getOrderDao();
        Order order1 = new Order(null,new Date(),customer.getId());
        Order order2 = new Order(null,new Date(),customer.getId());
        Order order3 = new Order(null,new Date(),customer.getId());

        orderDao.insert(order1);
        orderDao.insert(order2);
        orderDao.insert(order3);

        Toast.makeText(this, "add data finished...", Toast.LENGTH_SHORT).show();
    }


    public void btnShow(View view) {
        CustomerDao customerDao = mDBHelper.getDaoSession().getCustomerDao();
        Customer customer = customerDao.load(1L);

        if(null != customer) {
            List<Order> orderList = customer.getOrders();

            mTvInfo.setText(customer.toString() +"---->>" + orderList);

        }else {
            Toast.makeText(this, "get data null", Toast.LENGTH_SHORT).show();
        }

    }
}
