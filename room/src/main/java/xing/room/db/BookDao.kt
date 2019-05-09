package xing.room.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

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
interface BookDao {

    @Insert
    fun insertBook(vararg books: Book)

    @Query("select * from book")
    fun findAllBook() : List<Book>

    @Query("select * from book where bookId = (:bookId)")
    fun findAssignBook(bookId: Int) : Book


}