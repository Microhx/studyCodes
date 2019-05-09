package xing.room.db2

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/5/9
 *
 * version : 1.0.1
 *
 * desc :
 *
 *
 */
@Database(entities = [Person::class], version = 1)
abstract class UserPersonDatabase : RoomDatabase() {

    abstract fun getPersonDao() : PersonDao

}