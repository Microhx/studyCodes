package xing.room.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * author: Java
 * <p>
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 * <p>
 * date : 2019/5/6
 * <p>
 * version : 1.0.1
 * <p>
 * desc :
 */

@Database(entities = {User.class, Book.class}, version = 3)
public abstract class AbstractUserDataBase extends RoomDatabase {

    public  abstract UserDao getUserDao();

    public abstract BookDao getBookDao();

}
