package com.xing.workmanagerdome

import android.Manifest
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.work.*
import com.xing.workmanagerdome.logic.FileWorker
import com.xing.workmanagerdome.logic.UploadWorker
import java.util.concurrent.TimeUnit
import androidx.work.PeriodicWorkRequest
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // val uploadWorkRequest = OneTimeWorkRequestBuilder<UploadWorker>().build()
        // WorkManager.getInstance().enqueue(uploadWorkRequest)

        //WorkManager.getInstance().cancelAllWork()
        //addConstraints()

    }


    private fun addConstraints() {

        /**
         * 添加约束条件
         */
        val constraints = Constraints.Builder().
            //setRequiresDeviceIdle(true).
            //特定的网络状态
            //setRequiredNetworkType(NetworkType.CONNECTED).
            //电池在可接受的水平 [电量？]
            //setRequiresBatteryNotLow(true).
            //是否在充电时执行
            //setRequiresCharging(true).
            //存储是否满足 [容量知否足够]
            setRequiresStorageNotLow(true).build()

        val inputData = Data.Builder().putString("name", "Tom").putInt("age", 20).build()

        val uploadWorkRequest = OneTimeWorkRequestBuilder<UploadWorker>().setConstraints(constraints).
            //延时执行
            setInitialDelay(20, TimeUnit.SECONDS).setInputData(inputData).addTag("uploadImage").build()

        WorkManager.getInstance().enqueue(uploadWorkRequest)

        //cancel the task by tag
        //WorkManager.getInstance().cancelAllWorkByTag("tagName")
    }


    fun recurringWork(v: View) {
        val uploadWorker =
            PeriodicWorkRequestBuilder<UploadWorker>(5, TimeUnit.SECONDS).addTag("recurringWorkTask").build()
        WorkManager.getInstance().enqueue(uploadWorker)
    }

    fun cancelTask(v: View) {
        WorkManager.getInstance().cancelAllWorkByTag("recurringWorkTask")
        WorkManager.getInstance().cancelUniqueWork("")
        WorkManager.getInstance().cancelWorkById(UUID.randomUUID())

    }

    fun writeFile(v: View) {
        Log.d("write", "write file")


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE),
                    0
                )
            } else {
                gotoWriteFile()
            }

        } else {
            gotoWriteFile()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Log.d("onRequestPermission", "----->request result------>")


        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            gotoWriteFile()
        }
    }

    private fun gotoWriteFile() {
        Log.d("gotoWrite", "----->start to write file----")

        //val uploadWorker = PeriodicWorkRequestBuilder<FileWorker>(5, TimeUnit.SECONDS).addTag("recurringWorkTask").build()

        val saveRequest = PeriodicWorkRequest.Builder(FileWorker::class.java, 2, TimeUnit.SECONDS).build()
        WorkManager.getInstance().enqueue(saveRequest)
    }

    fun start(v: View) {

        //scroll_number_view.startAnimation()

        scroll_number_view.addValue()

    }


    fun desc(v: View) {

        scroll_number_view.descValue()
    }

}
