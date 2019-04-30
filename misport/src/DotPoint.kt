package com.qinghua.testenviroment.mi

import android.graphics.Point
import com.qinghua.testenviroment.dp2px
import java.util.*

/**
 * @author: Java
 * <p>
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 * <p>
 * <p>
 * @date : 2019/4/30
 * <p>
 * @version :
 * <p>
 * desc :
 */
class DotPoint(var offsetX: Float, var offsetY: Float, var radius : Float, var alpha : Int) {

    companion object {


        fun getDotList() : List<DotPoint> {
            val list = ArrayList<DotPoint>()

            list.add(DotPoint(0F,0F, dp2px(6f), 240))
            list.add(DotPoint(0F, -dp2px(16F), dp2px(5f), 220))
            list.add(DotPoint(-dp2px(12f), -dp2px(6F), dp2px(5f), 220))
            list.add(DotPoint(-dp2px(12f), -dp2px(14F), dp2px(6f), 220))
            list.add(DotPoint(-dp2px(14f), -dp2px(18F), dp2px(6f), 200))
            list.add(DotPoint(-dp2px(16f), -dp2px(24F), dp2px(5f), 200))
            list.add(DotPoint(dp2px(2f), -dp2px(24F), dp2px(5f), 180))

            list.add(DotPoint(dp2px(6f), -dp2px(30F), dp2px(3f), 180))

            list.add(DotPoint(dp2px(4f), -dp2px(26F), dp2px(4f), 160))
            list.add(DotPoint(-dp2px(16f), -dp2px(36F), dp2px(4f), 160))
            list.add(DotPoint(-dp2px(18f), -dp2px(42F), dp2px(4f), 160))
            list.add(DotPoint(-dp2px(12f), -dp2px(45F), dp2px(4f), 120))

            list.add(DotPoint(-dp2px(21f), -dp2px(50F), dp2px(4f), 120))
            list.add(DotPoint(-dp2px(6f), -dp2px(56F), dp2px(4f), 120))

            list.add(DotPoint(dp2px(6f), -dp2px(62F), dp2px(4f), 120))
            list.add(DotPoint(-dp2px(12f), -dp2px(68F), dp2px(4f), 120))


            //list.add(DotPoint(-dp2px(6f),0F, dp2px(18f), 0))
            //list.add(DotPoint(dp2px(10f), dp2px(12f), dp2px(12f), 80))
            //list.add(DotPoint(-dp2px(12f), dp2px(9f), dp2px(18f), 120))


            return list
        }
    }

}