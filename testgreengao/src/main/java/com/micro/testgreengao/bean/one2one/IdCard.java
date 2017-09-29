package com.micro.testgreengao.bean.one2one;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/25 - 15:13 <p>
 * interface :
 */

@Entity
public class IdCard {

    @Id(autoincrement = true)
    private Long _id ;

    private String cardName;

    private String location;

    @Generated(hash = 573573099)
    public IdCard(Long _id, String cardName, String location) {
        this._id = _id;
        this.cardName = cardName;
        this.location = location;
    }

    @Generated(hash = 1500073048)
    public IdCard() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getCardName() {
        return this.cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "IdCard{" +
                "_id=" + _id +
                ", cardName='" + cardName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
