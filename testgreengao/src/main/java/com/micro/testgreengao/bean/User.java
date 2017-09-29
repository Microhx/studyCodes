package com.micro.testgreengao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/25 - 10:20 <p>
 * interface :
 */
@Entity
public class User {

    @Id(autoincrement = true)
    @Property(nameInDb = "_id")
    private Long _id ;

    @Property(nameInDb = "_name" )
    private String name ;

    @Property(nameInDb = "_age")
    private int age ;

    //for db update params...
    //@Property(nameInDb = "_location")
    //private String location ;
    
    @Generated(hash = 704562150)
    public User(Long _id, String name, int age) {
        this._id = _id;
        this.name = name;
        this.age = age;
    }


    @Generated(hash = 586692638)
    public User() {
    }


    @Override
    public String toString() {
        return "User{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
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


    public int getAge() {
        return this.age;
    }


    public void setAge(int age) {
        this.age = age;
    }


}
