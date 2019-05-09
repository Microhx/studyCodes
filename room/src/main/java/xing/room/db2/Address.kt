package xing.room.db2

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

data class Address(

    val street:String? ,

    val state:String?,

    val city:String?
) {

    override fun toString(): String {
        return "Address(street=$street, state=$state, city=$city)"
    }
}
