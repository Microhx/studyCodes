package com.micro.testgreengao.bean.one2many2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import java.util.Date;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/25 - 16:44 <p>
 * interface :
 */

@Entity
public class Company {

    @Id(autoincrement = true)
    private Long _id;

    @NotNull
    private String successfulManName ;

    //当前公司名称
    private String companyName ;

    private java.util.Date date ;

    //销售额
    private double price;

    @Generated(hash = 1656804938)
    public Company(Long _id, @NotNull String successfulManName, String companyName,
            java.util.Date date, double price) {
        this._id = _id;
        this.successfulManName = successfulManName;
        this.companyName = companyName;
        this.date = date;
        this.price = price;
    }

    @Generated(hash = 1096856789)
    public Company() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getSuccessfulManName() {
        return this.successfulManName;
    }

    public void setSuccessfulManName(String successfulManName) {
        this.successfulManName = successfulManName;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public java.util.Date getDate() {
        return this.date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyName='" + companyName + '\'' +
                ", date=" + date +
                ", price=" + price +
                ", successfulManName='" + successfulManName + '\'' +
                ", _id=" + _id +
                '}';
    }
}
