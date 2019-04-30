package com.qinghua.testenviroment.mi

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import com.qinghua.testenviroment.dp2px
import java.util.*
import kotlin.math.min

/**
 * @author: Java
 * <p>
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 * <p>
 * <p>
 * @date : 2019/4/12
 * <p>
 * @version :
 * <p>
 * desc : 仿小米运动手环
 *
 * 1. 旋转阶段
 *    有数条运动的线 颜色为渐变色(由白色渐变为透明)
 *    线头有白色的圆点 位置大小不一
 *
 *
 *
 */
class SportView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val baseCircleRadius = dp2px(140F)

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val dotPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    /**
     * 圆环Paint
     */
    private val ringPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    /**
     * 圆环中发光的Paint
     */
    private val ringLightPaint = Paint(Paint.ANTI_ALIAS_FLAG)


    private val random = Random()

    private val mInnerMatrix = Matrix()

    private val dotPointList: List<DotPoint> = DotPoint.getDotList()

    private var currentStep = STEP_TWO

    private var stepTwoRect = RectF()

    var rotateDegrees = 0f
        set(value) {
            field = value
            invalidate()
        }

    var objectAnimator: ObjectAnimator? = null
    var ringScaleAnimator : ObjectAnimator ? = null
    var ringRotateAnimator : ObjectAnimator ? = null


    init {
        circlePaint.style = Paint.Style.STROKE
        circlePaint.color = Color.WHITE

        dotPaint.style = Paint.Style.FILL
        dotPaint.color = Color.WHITE


        ringPaint.style = Paint.Style.STROKE
        //ringPaint.color = Color.WHITE
        ringPaint.strokeWidth = dp2px(20f)

        ringLightPaint.style = Paint.Style.STROKE
        ringLightPaint.strokeWidth = dp2px(10f)
        ringLightPaint.color = Color.parseColor("#44FFFFFF")

    }

    val sweepColor = intArrayOf(Color.parseColor("#66FFFFFF"), Color.WHITE, Color.WHITE, Color.parseColor("#66FFFFFF"))
    val sweepPosition = floatArrayOf(0.0f, 0.3f, 0.7f, 1.0f)

    /**
     *  圆环圆心偏移x
     */
    var ringOffsetX = 0f

    /**
     * 圆环圆心偏移y
     */
    var ringOffsetY = 0f

    /**
     * 圆心偏移
     */
    var ringRadiusOffset = 0f

    /**
     * 变化的偏移量
     */
    var ringOffset = 0f   //0f 1f 0f
        set(value) {
            field = value

            //ringOffsetX = field * dp2px(20f)

            ringOffsetY = -field * dp2px(30f)
            ringRadiusOffset = field * dp2px(40f)

            invalidate()
        }


    var ringRotateDegrees = 0f
        set(value) {
            field = value

            invalidate()
        }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        circlePaint.shader = SweepGradient(w / 2.toFloat(), h / 2.toFloat(), Color.TRANSPARENT, Color.WHITE)

        ringPaint.shader = SweepGradient(w / 2.toFloat(), h / 2.toFloat(), sweepColor, sweepPosition)


        stepTwoRect.set(
            width / 2f - baseCircleRadius, height / 2f - baseCircleRadius,
            width / 2f + baseCircleRadius, height / 2f + baseCircleRadius
        )

    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (currentStep == STEP_ONE) {
            drawCircleAndPots(canvas)
        } else if (currentStep == STEP_TWO) {

            drawCircleRing(canvas!!)
        }
    }


    /**
     * 画圆环
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun drawCircleRing(canvas: Canvas) {
        canvas.save()

        mInnerMatrix.reset()
        mInnerMatrix.setRotate(ringRotateDegrees, width / 2f, height / 2f)
        canvas.matrix = mInnerMatrix

        for (i in 1..5) {
            canvas.drawArc(
                stepTwoRect.left - dp2px(2.4f) * i , stepTwoRect.top, stepTwoRect.right, stepTwoRect.bottom ,
                90F,
                180F,
                false,
                ringLightPaint
            )
        }

        canvas.drawCircle(width / 2f + ringOffsetX, height / 2f + ringOffsetY, baseCircleRadius + ringRadiusOffset, ringPaint)

        canvas.restore()
    }


    private fun drawCircleAndPots(canvas: Canvas?) {
        canvas!!.save()
        mInnerMatrix.reset()
        mInnerMatrix.setRotate(rotateDegrees, width / 2f, height / 2f)
        canvas.matrix = mInnerMatrix

        //draw circle
        for (circle in CirclePoint.getCircleList()) {
            circlePaint.strokeWidth = circle.strokeWidth
            canvas.drawCircle(
                (width / 2).toFloat() + circle.offsetX,
                (height / 2).toFloat() + circle.offsetY,
                baseCircleRadius + circle.radius,
                circlePaint
            )
        }


        //draw points
        for (point in dotPointList) {
            dotPaint.alpha = point.alpha
            canvas.drawCircle(
                (width / 2).toFloat() + baseCircleRadius + point.offsetX + (0.5f - random.nextFloat()) * DOT_PATH_OFFSET,
                (height / 2).toFloat() + point.offsetY + (0.5f - random.nextFloat()) * DOT_PATH_OFFSET,
                point.radius,
                dotPaint
            )

        }

        canvas.restore()
    }

    private fun getRingRotateAnimation() : ObjectAnimator {
        if(null == ringRotateAnimator) {
            ringRotateAnimator = ObjectAnimator.ofFloat(this,"ringRotateDegrees",0f,360f)
            ringRotateAnimator!!.duration = 5000
            ringRotateAnimator!!.interpolator = LinearInterpolator()
        }

        return ringRotateAnimator!!
    }


    private fun getRingScaleAnimation() : ObjectAnimator {
        if(null == ringScaleAnimator) {
            ringScaleAnimator = ObjectAnimator.ofFloat(this,"ringOffset", 0f, 1f, 0f)
            ringScaleAnimator!!.duration = 1000
            ringScaleAnimator!!.interpolator = AccelerateInterpolator()
        }

        return ringScaleAnimator!!
    }



    private fun getRotateAnimation(): ObjectAnimator {
        if (null == objectAnimator) {
            objectAnimator = ObjectAnimator.ofFloat(this, "rotateDegrees", 0f, 720f)
            objectAnimator!!.duration = 2000
            objectAnimator!!.interpolator = LinearInterpolator()
        }

        return objectAnimator!!
    }

    fun start() {
       // getRotateAnimation().start()

       // getRingScaleAnimation().start()

        getRingRotateAnimation().start()

    }

    companion object {

        /** 圆数量为15个*/
        const val RADIUS_COUNT = 6

        /**白色的小圆点*/
        const val WHITE_DOT_COUNT = 8

        const val DOT_PATH_OFFSET = 20f

        /** 第一步 画小白点和圆*/
        const val STEP_ONE = 1

        /** 第二步 画圆环*/
        const val STEP_TWO = 2


    }


}

