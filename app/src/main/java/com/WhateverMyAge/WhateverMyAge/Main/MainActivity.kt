package com.WhateverMyAge.WhateverMyAge.Main

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
import com.WhateverMyAge.WhateverMyAge.*
import com.WhateverMyAge.WhateverMyAge.Guide.Instruction.ChooseInstruction
import com.WhateverMyAge.WhateverMyAge.TravelAndFood.TravelAndFood
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.AdRequest

import com.WhateverMyAge.WhateverMyAge.Guide.Settings.SettingActivity
import com.WhateverMyAge.WhateverMyAge.Practice.PracticeActivity

class MainActivity : AppCompatActivity() {

    val permission_list = arrayOf(
        WRITE_CONTACTS,
        ACCESS_COARSE_LOCATION,
        ACCESS_FINE_LOCATION
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
            //설명서 화면
            val intent = Intent(this, ChooseInstruction::class.java)
            startActivity(intent)
        }
        bt_as.setOnClickListener {
            //수리 화면
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        bt_practice.setOnClickListener {
            val intent = Intent(this, PracticeActivity::class.java)
            startActivity(intent)
        }
        bt_travelandfood.setOnClickListener {
            val intent = Intent(this, TravelAndFood::class.java)
            intent.putExtra("test", 2)
            startActivity(intent)
        }
//        MobileAds.initialize(this, getString(R.string.admob_app_id))
//        val extras = Bundle()
//        extras.putString("max_ad_content_rating", "G") // 앱이 3세 이상 사용가능이라면 광고레벨을 설정해줘야 한다
//        val adRequest = AdRequest.Builder()
//            .addNetworkExtrasBundle(AdMobAdapter::class.java, extras)
//            .build()
//        adView.loadAd(adRequest)

        MobileAds.initialize(this, getString(R.string.admob_app_id))
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        // 광고가 제대로 로드 되는지 테스트 하기 위한 코드입니다.
        adView.setAdListener(object : AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                // 광고가 문제 없이 로드시 출력됩니다.
                Log.d("@@@", "정상")
            }
            override fun onAdFailedToLoad(errorCode: Int) {
                // Code to be executed when an ad request fails.
                // 광고 로드에 문제가 있을시 출력됩니다.
                Log.d("@@@", "onAdFailedToLoad $errorCode")
            }
            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }
            override fun onAdClicked() {
                Log.d("@@@", "클릭됨")
                // Code to be executed when the user clicks on an ad.

            }


            override fun onAdLeftApplication() {

                // Code to be executed when the user has left the app.

            }


            override fun onAdClosed() {

                // Code to be executed when the user is about to return

                // to the app after tapping on an ad.

            }

        })
    }

    override fun onStart() {
        super.onStart()
//        if (signedin == 0)
//            bt_login.text = "로그인"
//        else
//            bt_login.text = "내 정보"
        num = random.nextInt(27)
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