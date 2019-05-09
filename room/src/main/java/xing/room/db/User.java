package xing.room.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import kotlin.jvm.Transient;


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
@Entity(tableName = "t_user")
public class User {

    public User() { }


    @PrimaryKey(autoGenerate = true)
    public long id ;


    @ColumnInfo(name = "user_name")
    public String userName;

    public String address ;

    @ColumnInfo(name = "age", typeAffinity = ColumnInfo.INTEGER)
    public int age ;

    public boolean sex ;

    @Ignore
    @Transient
    public User boyFriend;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }
}
