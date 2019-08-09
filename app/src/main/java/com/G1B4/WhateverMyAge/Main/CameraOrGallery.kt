package com.G1B4.WhateverMyAge.Main

import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.view.Window
import com.G1B4.WhateverMyAge.R
import kotlinx.android.synthetic.main.activity_camera_or_gallery.*

class CameraOrGallery : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_camera_or_gallery)

        bt_camera.setOnClickListener {
            setResult(3)
            finish()
        }


        bt_gallery.setOnClickListener {
            setResult(4)
           finish()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }

        return super.onTouchEvent(event)
    }
}
