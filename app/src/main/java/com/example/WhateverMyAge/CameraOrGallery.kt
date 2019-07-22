package com.example.WhateverMyAge

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.Window
import kotlinx.android.synthetic.main.activity_camera_or_gallery.*

class CameraOrGallery : Activity() {

    val camera = CameraUpload()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        setContentView(R.layout.activity_camera_or_gallery)

        bt_camera.setOnClickListener {
            val intent = Intent()
            intent.putExtra("result", 3)
            Log.i("사진 찍자", "d")
            setResult(3)
            Log.i("이게 안 돼?", "$RESULT_OK")
            finish()
            //camera.captureFromCamera()
            //finishActivity(3)
        }


        bt_gallery.setOnClickListener {
            val intent = Intent()
            intent.putExtra("result", 4)
            Log.i("갤러리 찍자", "d")
            setResult(4)
            Log.i("이게 안 돼?", "$RESULT_OK")
           finish()
            //camera.pickFromGallery()
            //finishActivity(4)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }

        return super.onTouchEvent(event)
    }
}
