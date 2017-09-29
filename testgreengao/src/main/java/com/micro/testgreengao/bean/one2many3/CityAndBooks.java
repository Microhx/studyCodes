package com.micro.testgreengao.bean.one2many3;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/25 - 17:35 <p>
 * interface :
 */

@Entity
public class CityAndBooks {

    @Id
    private Long _id;

    private long bookId;

    private long cityId ;

    @Generated(hash = 958227800)
    public CityAndBooks(Long _id, long bookId, long cityId) {
        this._id = _id;
        this.bookId = bookId;
        this.cityId = cityId;
    }

    @Generated(hash = 908449729)
    public CityAndBooks() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public long getBookId() {
        return this.bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getCityId() {
        return this.cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }
}
