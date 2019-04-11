package com.xing.protobuflearn

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.xing.protobuflearn.entity.Login
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(view: View) {
        gotoNetwork()

    }

    private fun gotoNetwork() {
        val username = id_et_name.text.toString()
        val password = id_et_password.text.toString()
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "please fill username and password", Toast.LENGTH_SHORT).show()
            return
        }

        //序列化封装
        val login = Login.LoginRequest.newBuilder().setUsername(username).setPassword(password).build()

        val requestBody = FormBody.create(MediaType.get("application/octet-stream"), login.toByteArray())
        val request = Request.Builder().url("http://192.168.20.206:8080/index/login").post(requestBody)
            .build()
        val call = OkHttpClient().newCall(request)


        call.enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val loginResult = Login.LoginResponse.parseFrom(response.body()?.bytes())
                Log.i(TAG, "login result:" + loginResult.code + "--" + loginResult.msg)


                runOnUiThread {
                    Toast.makeText(this@MainActivity,"${loginResult.code}---->${loginResult.msg}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()

                Log.e(TAG, "login error")
            }
        })

    }

    companion object {
        var TAG = "MainActivity"
    }
}
