package xing.room.db;

import androidx.room.*;

import java.util.List;

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
@Dao
public interface UserDao {

    @Query("SELECT * FROM t_user")
    List<User> queryAllUserInfo();


    @Query("SELECT * FROM t_user WHERE id = :id")
    User getUserById(long id);


    @Insert(onConflict = OnConflictStrategy.REPLACE )
    long[] insertUser(User... users);


    @Update
    void updateUser(User user);


    @Delete
    void deleteUser(User user);


}
