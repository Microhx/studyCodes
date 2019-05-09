package xing.room

import androidx.room.Room
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import xing.room.db.AbstractUserDataBase
import xing.room.db.User
import xing.room.db2.Address
import xing.room.db2.Person
import xing.room.db2.UserPersonDatabase
import xing.room.db3.PlaySongJoin
import xing.room.db3.PlaySongDataBase
import xing.room.db3.Playlist
import xing.room.db3.Song

class MainActivity : AppCompatActivity() {

    lateinit var userDataBase: AbstractUserDataBase
    lateinit var personDatabase: UserPersonDatabase
    lateinit var songDataBase: PlaySongDataBase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUserDao()
    }

    private fun initUserDao() {
        userDataBase = Room.databaseBuilder(applicationContext,AbstractUserDataBase::class.java,"userDataBase").build()

        Log.d("TAG","userDataBase:$userDataBase")

        personDatabase = Room.databaseBuilder(applicationContext, UserPersonDatabase::class.java, "personDataBase").build()

        songDataBase = Room.databaseBuilder(applicationContext, PlaySongDataBase::class.java, "songDataBase").build()

    }

    fun queryData(view:View) {
        val absolutePath  = getDatabasePath("userDataBase").absolutePath
        Log.w("absolutePath","---->$absolutePath")

        Thread{

            val listInfo = userDataBase.userDao.queryAllUserInfo()
            Log.d("queryDataInfo","listInfoSize: ${listInfo.size}")

            for(u in listInfo){
                Log.d("queryDataUserInfo","--->$u")
            }

        }.start()
    }


    fun saveData(view: View) {

        val u = User()
        u.userName = "Tom"
        u.address = "shanghai"
        u.sex = true
        u.age = 20

        Thread {
            Log.w("threadMode","current thread mode : ${Thread.currentThread().name}")

            for (i in 0..100) {
                val result =  userDataBase.userDao.insertUser(u)
                Log.d("saveData","--->${result[0]}")
            }

        }.start()

    }

    fun personAndAddressAdd(v:View) {

        Thread{

            for (i in 0..100) {
                val address = Address("street one $i", "state two $i", "city three $i")
                val p = Person(0, "tom + $i", 20, address)


                personDatabase.getPersonDao().savePersonInfo(p)

                Log.d("tag", "finished")
            }

        }.start()

    }

    fun personAndAddressUpdate(v:View) {

        Thread {
            //val address = Address("street one1", "state two2", "city three3")
            //val p = Person(1,"marry",0, address)
            //personDatabase.getPersonDao().updatePerson(p)

            personDatabase.getPersonDao().updatePersonName(1,"hello world")

            Log.w("update","update finished")


        }.start()

    }

    fun personAndAddressQuery(v:View) {

        Thread {
            val person = personDatabase.getPersonDao().queryPersonInfo(2)

            Log.d("query","person: $person")

        }.start()

    }

    fun personAndAddressDelete(v:View) {

        Thread {

            val p = Person(1, null,0,null)
            personDatabase.getPersonDao().deletePerson(p)

            Log.d("delete","done")

        }.start()
    }

    fun songAdd(view : View) {
        Thread {

            //val song = Song(0,"光年之外","邓紫棋")
            //songDataBase.getPlaySongJoinDao().insertSong(song)

            val song = Song(0,"just on the fire", "just somebody")
            songDataBase.getPlaySongJoinDao().insertSong(song)

            val palylist = Playlist(0,"my favorite song", "I like it")
            songDataBase.getPlaySongJoinDao().insertPlayList(palylist)

            songDataBase.getPlaySongJoinDao().insert(PlaySongJoin(1,1))

            Log.d("add done", "done")

        }.start()

    }

    fun songQuery(view:View) {

        Thread{

            val songsList = songDataBase.getPlaySongJoinDao().getSonsForPlayList(1)
            for(song in songsList){
                Log.w("songQuery","the song is $song")
            }

            val playList = songDataBase.getPlaySongJoinDao().getPlayListsForSongs(1)
            for(p in playList) {
                Log.w("playList","the play list is $p")
            }

        }.start()


    }

}
