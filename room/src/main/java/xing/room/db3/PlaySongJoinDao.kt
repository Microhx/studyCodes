package xing.room.db3

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
interface PlaySongJoinDao {

    @Insert
    fun insertSong(song:Song) : Long

    @Insert
    fun insertPlayList(playlist: Playlist) : Long

    @Insert
    fun insert(playSonJoin:PlaySongJoin) : Long

    @Query("""
        SELECT * from playlist
        INNER JOIN play_song_join
        ON playlist.id = play_song_join.play_list_id
        WHERE play_song_join.song_id = (:songId)
    """)
    fun getPlayListsForSongs(songId:Int) : List<Playlist>


    @Query("""
        SELECT * FROM song
        INNER JOIN play_song_join
        ON song.id = play_song_join.song_id
        WHERE play_song_join.play_list_id = (:playListId)
    """)
    fun getSonsForPlayList(playListId:Int) : List<Song>


}