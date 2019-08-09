package com.G1B4.WhateverMyAge.Main

import com.G1B4.WhateverMyAge.Guide.GuideActivity
import android.Manifest.permission.*
import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.content.pm.PackageManager
import android.os.AsyncTask
import android.os.Build
import android.util.Log
import com.G1B4.WhateverMyAge.*
import com.G1B4.WhateverMyAge.Love.LoveActivity
import com.G1B4.WhateverMyAge.TravelAndFood.TravelAndFood

class MainActivity : AppCompatActivity() {
    val permission_list = arrayOf(
        WRITE_CONTACTS,
        ACCESS_COARSE_LOCATION,
        ACCESS_FINE_LOCATION,
        READ_EXTERNAL_STORAGE,
        WRITE_EXTERNAL_STORAGE,
        CAMERA
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        checkPermission()
        for (permission in permission_list) {
            //권한 허용 여부를 확인한다.
            val chk = checkCallingOrSelfPermission(permission)
            if (chk != PackageManager.PERMISSION_DENIED) {
                Log.e("권한거절", "::$permission")
            }
            else{
                Log.e("권한", "::$permission")
            }
        }

        quote.text = quotelist[num].Quote
        quotedfrom.text = quotelist[num].QuotedFrom


        bt_guide.setOnClickListener {
            //setting 화면
            val intent = Intent(this, GuideActivity::class.java)
            startActivity(intent)
        }
        bt_love.setOnClickListener {
            //사랑방 화면

            val intent = Intent(this, LoveActivity::class.java)
            startActivity(intent)


        }
        bt_login.setOnClickListener {
            //로그인 화면
            if (signedin == 0) {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, MyInformation::class.java)
                val arr : Array<String> = arrayOf(signedin.toString(), user_name)
                intent.putExtra("user_info", arr)
                startActivity(intent)
            }
        }
        bt_travelandfood.setOnClickListener {
            //loading

            val intent = Intent(this, TravelAndFood::class.java)
            intent.putExtra("test", 2)
            startActivity(intent)

        }
    }

    override fun onStart() {
        super.onStart()
        if (signedin == 0)
            bt_login.text = "로그인"
        else
            bt_login.text = "내 정보"
        num = random.nextInt(5)
        quote.text = quotelist[num].Quote
        quotedfrom.text = quotelist[num].QuotedFrom


    }
    fun checkPermission() {
        //현재 안드로이드 버전이 6.0미만이면 메서드를 종료한다.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return

        for (permission in permission_list) {
            //권한 허용 여부를 확인한다.
            val chk = checkCallingOrSelfPermission(permission)

            if (chk == PackageManager.PERMISSION_DENIED) {
                //권한 허용을여부를 확인하는 창을 띄운다
                requestPermissions(permission_list, 0)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0) {
            for (i in grantResults.indices) {
                //허용됬다면
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    Toast.makeText(applicationContext, "앱권한설정하세요", Toast.LENGTH_LONG).show()

                    //finish()
                }
                //bt_guide.text= i.toString()
            }

        }
    }
    private inner class CheckTypesTask : AsyncTask<Void, Void, Void>() {

        internal var asyncDialog = ProgressDialog(this@MainActivity)

        override fun onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            asyncDialog.setMessage("로딩중입니다..")

            // show dialog
            asyncDialog.show()
            super.onPreExecute()
        }

        override fun doInBackground(vararg arg0: Void): Void? {
            try {
                for (i in 0..4) {
                    //asyncDialog.setProgress(i * 30);
                    Thread.sleep(500)
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(result: Void) {
            asyncDialog.dismiss()
            super.onPostExecute(result)
        }
    }
}