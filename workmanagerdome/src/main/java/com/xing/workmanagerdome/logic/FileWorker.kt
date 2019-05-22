package com.xing.workmanagerdome.logic

import android.content.Context
import android.os.Environment
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.lang.Exception

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
class FileWorker(appContext: Context, workerParameters: WorkerParameters) : Worker(appContext, workerParameters) {
    override fun doWork(): Result {
        Log.d("doWork", "doWork start")

        try {
            val file = File(Environment.getExternalStorageDirectory(), "1.txt")
            BufferedWriter(FileWriter(file)).append("hello world \n").close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        Log.d("doWork","doWork end")

        return Result.success()
    }

}