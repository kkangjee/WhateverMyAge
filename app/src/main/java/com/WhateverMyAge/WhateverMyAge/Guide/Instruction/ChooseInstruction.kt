package com.WhateverMyAge.WhateverMyAge.Guide.Instruction


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.WhateverMyAge.WhateverMyAge.R
import kotlinx.android.synthetic.main.activity_explanation.*
import android.app.Activity
import android.app.ActivityManager
import android.os.Process
import android.content.pm.ResolveInfo
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds

class ChooseInstruction : AppCompatActivity() {
    val PERMISSION_REQUEST_CODE = 1

    fun getPackageList(packagename: String): Boolean {
        var isExist = false

        val pkgMgr = packageManager
        val mApps: List<ResolveInfo>
        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        mApps = pkgMgr.queryIntentActivities(mainIntent, 0)

        try {
            for (i in mApps.indices) {
                if (mApps[i].activityInfo.packageName.startsWith(packagename)) {
                    isExist = true
                    break
                }
            }
        } catch (e: Exception) {
            isExist = false
        }

        return isExist
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explanation)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            val intent = Intent(
                Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:$packageName")
            )
            startActivityForResult(intent, PERMISSION_REQUEST_CODE)
        }

        bt_howtomessage.setOnClickListener {
            if (getPackageList("com.kakao.talk")) {
                showChatHead(0)

                val componentName =
                    packageManager.getLaunchIntentForPackage("com.kakao.talk")!!.component
                val intent = Intent.makeRestartActivityTask(componentName)
                startActivity(intent)
            }
        }

        bt_howtofriend.setOnClickListener {
            if (getPackageList("com.kakao.talk")) {
                showChatHead(1)

                val componentName =
                    packageManager.getLaunchIntentForPackage("com.kakao.talk")!!.component
                val intent = Intent.makeRestartActivityTask(componentName)
                startActivity(intent)
            }
        }

        bt_howtopic.setOnClickListener {
            if (getPackageList("com.kakao.talk")) {
                showChatHead(2)

                val componentName =
                    packageManager.getLaunchIntentForPackage("com.kakao.talk")!!.component
                val intent = Intent.makeRestartActivityTask(componentName)
                startActivity(intent)
            }
        }

        bt_howtoplayyoutube.setOnClickListener {
            if (getPackageList("com.google.android.youtube")) {

                val am = getSystemService(Activity.ACTIVITY_SERVICE) as ActivityManager
                am.killBackgroundProcesses("com.google.android.youtube")
                showChatHead(3)

                val componentName =
                    packageManager.getLaunchIntentForPackage("com.google.android.youtube")!!.component
                val intent = Intent.makeRestartActivityTask(componentName)
                startActivity(intent)
            }
        }

        bt_back.setOnClickListener {
            finish()

        }
        MobileAds.initialize(this, getString(R.string.admob_app_id))
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                showChatHead(1)
            }
        }
    }

    fun showChatHead(which: Int) {
        val intent = Intent(this, ChatHead::class.java)
        intent.putExtra("which", which)
        startService(intent)
        Log.i("www", Integer.toString(which) + " 를 전달함")
    }
}