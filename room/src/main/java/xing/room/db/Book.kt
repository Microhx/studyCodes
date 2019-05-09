package xing.room.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
@Entity(foreignKeys = [ForeignKey(
    entity = User::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("user_id"),
    onDelete = ForeignKey.NO_ACTION,
    onUpdate = ForeignKey.NO_ACTION
)])

data class Book (

    @PrimaryKey(autoGenerate = true)
    val bookId : Int,

    val title: String?,

    @ColumnInfo(name = "user_id")
    val userId:Int
)