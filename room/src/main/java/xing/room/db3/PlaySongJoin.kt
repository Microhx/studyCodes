package xing.room.db3

import androidx.room.Entity
import androidx.room.ForeignKey

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
@Entity(tableName = "play_song_join",
    primaryKeys = ["play_list_id","song_id"],
    foreignKeys = [
        ForeignKey(entity = Playlist::class,
            parentColumns = ["id"],
            childColumns = ["play_list_id"]),

        ForeignKey(entity = Song::class,
            parentColumns = ["id"],
            childColumns = ["song_id"])
    ]
)
data class PlaySongJoin(
    val play_list_id: Int,
    val song_id : Int
)