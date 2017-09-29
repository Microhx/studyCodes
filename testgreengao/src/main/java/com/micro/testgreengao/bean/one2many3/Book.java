package com.micro.testgreengao.bean.one2many3;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/25 - 17:33 <p>
 * interface :
 */

@Entity
public class Book {

    @Id
    private Long _id;

    private String name ;

    private double price ;

    @Generated(hash = 2038125984)
    public Book(Long _id, String name, double price) {
        this._id = _id;
        this.name = name;
        this.price = price;
    }

    @Generated(hash = 1839243756)
    public Book() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
