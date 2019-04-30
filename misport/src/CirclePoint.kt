package com.qinghua.testenviroment.mi

import com.qinghua.testenviroment.dp2px

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
class CirclePoint(
    var offsetX: Float,
    val offsetY: Float,
    val radius: Float,
    val strokeWidth: Float
) {

    companion object {

        fun getCircleList(): List<CirclePoint> {
            val list = ArrayList<CirclePoint>()

            list.add(CirclePoint(dp2px(2.2f), dp2px(0f), -dp2px(2.2f), dp2px(1.1f)))
            list.add(CirclePoint(-dp2px(1.5f), dp2px(3.4f), dp2px(2.2f), -dp2px(1.2f)))
            list.add(CirclePoint(dp2px(5.5f), -dp2px(2.2f), dp2px(2.4f), dp2px(1.4f)))
            list.add(CirclePoint(-dp2px(4.3f), dp2px(5.5f), dp2px(2.5f), dp2px(2.2f)))
            list.add(CirclePoint(-dp2px(2.41f), -dp2px(4.6f), dp2px(2.1f), -dp2px(2.4f)))
            list.add(CirclePoint(dp2px(3.3f), -dp2px(3.3f), dp2px(2.3f), dp2px(1.8f)))
            list.add(CirclePoint(dp2px(2f), dp2px(2.3f), -dp2px(1.7f), dp2px(1.8f)))
            list.add(CirclePoint(dp2px(1.7f), dp2px(2.7f), -dp2px(1.6f), dp2px(2.1f)))
            list.add(CirclePoint(dp2px(2f), dp2px(5.3f), -dp2px(1.8f), dp2px(2.2f)))
            list.add(CirclePoint(dp2px(2.3f), dp2px(1.4f), -dp2px(2.3f), dp2px(1.8f)))

            return list
        }
    }



}