package xing.room.db3

import androidx.room.Entity
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
@Entity
data class Playlist(
    @PrimaryKey(autoGenerate = true) val id : Int,
    val name:String?,
    val description:String?
)