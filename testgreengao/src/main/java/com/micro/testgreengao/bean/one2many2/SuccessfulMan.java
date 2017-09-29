package com.micro.testgreengao.bean.one2many2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinProperty;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.OrderBy;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.micro.testgreengao.greendao.gen.DaoSession;
import com.micro.testgreengao.greendao.gen.CompanyDao;
import com.micro.testgreengao.greendao.gen.SuccessfulManDao;

/**
 * author : micro_hx <p>
 * desc : <p>
 * email: javainstalling@163.com <p>
 * date : 2017/9/25 - 16:43 <p>
 * interface :
 */

@Entity
public class SuccessfulMan {

    @Id(autoincrement = true)
    private Long _id;

    @NotNull
    private String name;

    private int age ;

    @ToMany(joinProperties = {
       @JoinProperty(name = "name",referencedName = "successfulManName")
    })
    @OrderBy("date ASC")
    private List<Company> companyList;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1305244650)
    private transient SuccessfulManDao myDao;

    @Generated(hash = 103778917)
    public SuccessfulMan(Long _id, @NotNull String name, int age) {
        this._id = _id;
        this.name = name;
        this.age = age;
    }

    @Generated(hash = 731863927)
    public SuccessfulMan() {
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1350392234)
    public List<Company> getCompanyList() {
        if (companyList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CompanyDao targetDao = daoSession.getCompanyDao();
            List<Company> companyListNew = targetDao
                    ._querySuccessfulMan_CompanyList(name);
            synchronized (this) {
                if (companyList == null) {
                    companyList = companyListNew;
                }
            }
        }
        return companyList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1637942205)
    public synchronized void resetCompanyList() {
        companyList = null;
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
    @Generated(hash = 1470812782)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getSuccessfulManDao() : null;
    }


    @Override
    public String toString() {
        return "SuccessfulMan{" +
                "age=" + age +
                ", _id=" + _id +
                ", name='" + name + '\'' +
                '}';
    }
}
