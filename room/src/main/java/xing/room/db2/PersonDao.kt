package xing.room.db2

import androidx.room.*

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
@Dao
interface PersonDao {

    @Insert
    fun savePersonInfo(person: Person)

    @Query("select * from t_person where id = (:id)")
    fun queryPersonInfo(id:Int) : Person

    @Query("update t_person set name = (:newName) where id = (:id)")
    fun updatePersonName(id:Int, newName:String) : Int

    @Delete
    fun deletePerson(p:Person)

    @Update
    fun updatePerson(p:Person)
}