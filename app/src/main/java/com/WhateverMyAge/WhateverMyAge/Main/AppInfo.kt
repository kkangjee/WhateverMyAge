package com.WhateverMyAge.WhateverMyAge.Main

import android.content.pm.PackageInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_app_info.*
import java.security.AccessController.getContext
import android.os.Build
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.ShapeDrawable
import android.content.Intent
import android.net.Uri


class AppInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.WhateverMyAge.WhateverMyAge.R.layout.activity_app_info)

        val info: PackageInfo = this.packageManager.getPackageInfo("com.WhateverMyAge.WhateverMyAge", 0)
        val version = info.versionName
        versionNumber.text = "v" + version.toString()

        icon.setBackground(ShapeDrawable(OvalShape()))
        icon.setClipToOutline(true)

        bt_back.setOnClickListener {
            finish()
        }

        bt_review.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("market://details?id=com.WhateverMyAge.WhateverMyAge")
            startActivity(intent)
        }
    }
}
