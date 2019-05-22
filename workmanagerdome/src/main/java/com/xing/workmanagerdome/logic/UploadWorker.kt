package com.xing.workmanagerdome.logic

import android.content.Context
import android.os.Build.VERSION_CODES.P
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * author: Java
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2019/5/22
 *
 * version : 1.0.1
 *
 * desc :
 *
 *
 */
class UploadWorker(appContext: Context, workParams:WorkerParameters) : Worker(appContext, workParams) {

    override fun doWork(): Result {

        val name = inputData.getString("name")
        val age = inputData.getInt("age", 0)

        Log.d("inputData", "name:$name , age: $age")

        Log.d("doWork","Thread : ${Thread.currentThread().name}")

        Log.d("task","task begin : ")
        Log.d("task","doing...")
        Log.d("task","task finished :")


        return Result.success()
    }


}