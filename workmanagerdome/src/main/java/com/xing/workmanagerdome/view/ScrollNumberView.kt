package com.xing.workmanagerdome.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import java.util.*
import kotlin.collections.ArrayList

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/5/22
 *
 * version : 1.0.1
 *
 * desc : 滚动的数字
 *
 *
 */
class ScrollNumberView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val currentPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val nextPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    //当前数字
    private var mCount = 98

    //文字的高度
    private var mTextHeight = 0f

    //单个字符的宽度
    private var mCharacterWidth = 0f

    //数据集合
    private val mDataList = ArrayList<NumberUnit>()

    //当前text的宽度
    private var mCurrentTextWidth = 0f

    private var valueAnimator: ValueAnimator?= null

    //笔画的透明度
    private var paintAlpha = 0f


    //是否为添加数据
    private var isAddData: Boolean = true


    private var heightOffset = 0f
                set(value) {
                    field = value
                    invalidate()
                }

    init {
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.textSize = 30f
        paint.color = Color.parseColor("#4527A0")

        currentPaint.style = Paint.Style.FILL_AND_STROKE
        currentPaint.textSize = 30f
        currentPaint.color = Color.parseColor("#4527A0")


        nextPaint.style = Paint.Style.FILL_AND_STROKE
        nextPaint.textSize = 30f
        nextPaint.color = Color.parseColor("#4527A0")


        val fm = paint.fontMetricsInt
        mTextHeight = (fm.descent - fm.ascent) / 2f

        mCharacterWidth = paint.measureText("0")


        Log.d("textHeight","textHeight : $mTextHeight")

        collectData(mCount)

        mCurrentTextWidth = 20f

    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        mCurrentTextWidth = 20f
        for (unit in mDataList) {
            val str = getUnitValue(unit)


            //数据没有变化 那么什么都不变
            if(unit.nextValue == unit.currentValue) {
                canvas!!.drawText(str, mCurrentTextWidth ,  (height + mTextHeight) / 2f , paint)

            }else {

                if(unit.currentValue != NO_VALUE) {
                    currentPaint.alpha = (255 * (1 - paintAlpha)) .toInt()  //255 全黑
                    canvas!!.drawText(unit.currentValue.toString() , mCurrentTextWidth ,  heightOffset + (height + mTextHeight) / 2f , currentPaint)
                }


                if(unit.nextValue != NO_VALUE) {
                    nextPaint.alpha =  (255 * paintAlpha).toInt()
                    canvas!!.drawText(unit.nextValue.toString() , mCurrentTextWidth ,   (mTextHeight * 3f + heightOffset) + (height + mTextHeight) / 2f , nextPaint)
                }
            }

            mCurrentTextWidth += mCharacterWidth
        }
    }

    private fun getUnitValue(unit: NumberUnit): String {
        if(unit.currentValue != NO_VALUE) return unit.currentValue.toString()
        if(unit.nextValue != NO_VALUE) return unit.nextValue.toString()
        return ""

    }

    /**
     * 现将数据拆分成集合
     */
    private fun collectData(count: Int) {
        mDataList.clear()
        for(temp in convertData(count)) {
            mDataList.add(NumberUnit(temp))
        }

        Log.d("data","collectData: $mDataList")
    }


    /**
     * 添加一个数值
     */
    fun addValue() {
        isAddData = true
        mDataList.clear()

        val tempList = convertData(mCount)
        val newList = convertData(mCount+1)

        var newListOffset = 0
        //说明进位
        if(newList.size > tempList.size) {
            mDataList.add(NumberUnit(NO_VALUE, newList[0]))
            newListOffset = 1
        }

        for (index in 0 until tempList.size) {
            mDataList.add(NumberUnit(tempList[index], newList[newListOffset + index]))
        }

        mCount ++

        startMyAnimation(true)
    }

    /**
     * 值减小
     */
    fun descValue() {
        isAddData = false
        mDataList.clear()

        val tempList = convertData(mCount)
        val newList = convertData(mCount - 1)

        var currentOffset = 0
        if(tempList.size > newList.size) {
            mDataList.add(NumberUnit(tempList[0], NO_VALUE))
            currentOffset = 1
        }

        for(index in 0 until newList.size) {
            mDataList.add(NumberUnit(tempList[index + currentOffset], newList[index]))
        }

        mCount --

        startMyAnimation(false)
    }


    private fun convertData(data:Int) : List<Int> { //100
        val list = ArrayList<Int>()
        var tempValue = data

        //100 > 10
        while (tempValue >= 10) {
            val unit = tempValue % 10
            tempValue /= 10

            list.add(unit)
        }

        if(tempValue > 0) {
            list.add(tempValue)
        }

        list.reverse()

        return list
    }


    private fun startMyAnimation(increase :Boolean) {
        valueAnimator?.apply {
            if(isRunning) cancel()
        }

        val start = if(increase) {
            0f
        } else {
            1f
        }

        val end = if(increase){
            1f
        } else {
            0f
        }

        valueAnimator = ValueAnimator.ofFloat(start,end).setDuration(500)
        valueAnimator?.apply {
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { animation ->
                run {
                    heightOffset = -mTextHeight * 3f * animation.animatedValue as Float
                    paintAlpha = animatedValue as Float
                }
            }
            start()
        }
    }


    companion object {
        const val NO_VALUE = -1


    }

}