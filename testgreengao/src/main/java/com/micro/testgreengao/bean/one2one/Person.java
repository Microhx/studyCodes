package com.micro.testgreengao.bean.one2one;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;
import com.micro.testgreengao.greendao.gen.DaoSession;
import com.micro.testgreengao.greendao.gen.IdCardDao;
import com.micro.testgreengao.greendao.gen.PersonDao;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/25 - 15:12 <p>
 * interface :
 */

@Entity
public class Person {

    @Id(autoincrement = true)
    private Long _id;

    private String name ;

    private Long idCardId ;

    @ToOne(joinProperty = "idCardId")
    private IdCard  idCard;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 778611619)
    private transient PersonDao myDao;

    @Generated(hash = 602561657)
    private transient Long idCard__resolvedKey;

    @Generated(hash = 609694259)
    public Person(Long _id, String name, Long idCardId) {
        this._id = _id;
        this.name = name;
        this.idCardId = idCardId;
    }

    @Generated(hash = 1024547259)
    public Person() {
    }


    @Override
    public String toString() {
        return "Person{" +
                "_id=" + _id +
                ", idCard=" + getIdCard() +
                ", name='" + name + '\'' +
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

    public Long getIdCardId() {
        return this.idCardId;
    }



    public void setIdCardId(Long idCardId) {
        this.idCardId = idCardId;
    }



    /** To-one relationship, resolved on first access. */
    @Generated(hash = 171237768)
    public IdCard getIdCard() {
        Long __key = this.idCardId;
        if (idCard__resolvedKey == null || !idCard__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            IdCardDao targetDao = daoSession.getIdCardDao();
            IdCard idCardNew = targetDao.load(__key);
            synchronized (this) {
                idCard = idCardNew;
                idCard__resolvedKey = __key;
            }
        }
        return idCard;
    }



    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 460486455)
    public void setIdCard(IdCard idCard) {
        synchronized (this) {
            this.idCard = idCard;
            idCardId = idCard == null ? null : idCard.get_id();
            idCard__resolvedKey = idCardId;
        }
    }



    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }



    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }



    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }



    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2056799268)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getPersonDao() : null;
    }



}
