package com.micro.testgreengao.bean.one2many1;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import java.util.Date;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/25 - 16:08 <p>
 * interface :
 */

@Entity(nameInDb = "t_order")
public class Order {

    @Id
    private Long _id ;

    private java.util.Date date ;

    private long customerId ;

    @Generated(hash = 1808226414)
    public Order(Long _id, java.util.Date date, long customerId) {
        this._id = _id;
        this.date = date;
        this.customerId = customerId;
    }


    @Generated(hash = 1105174599)
    public Order() {
    }


    @Override
    public String toString() {
        return "Order{" +
                "_id=" + _id +
                ", date=" + date +
                ", customerId=" + customerId +
                '}';
    }


    public Long get_id() {
        return this._id;
    }


    public void set_id(Long _id) {
        this._id = _id;
    }


    public java.util.Date getDate() {
        return this.date;
    }


    public void setDate(java.util.Date date) {
        this.date = date;
    }


    public long getCustomerId() {
        return this.customerId;
    }


    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
