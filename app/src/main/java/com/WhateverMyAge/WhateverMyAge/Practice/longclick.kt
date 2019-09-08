package com.WhateverMyAge.WhateverMyAge.Practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.WhateverMyAge.WhateverMyAge.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import kotlinx.android.synthetic.main.activity_explanation_fragment.*
import kotlinx.android.synthetic.main.activity_longclick.*



class longclick : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_longclick)



        val res = resources
        val gifImage = GlideDrawableImageViewTarget(longclick)
        val slide = res.getIdentifier("longclick", "drawable", this.packageName)

        Glide.with(this).load(slide).into(gifImage)

        bt_long.setOnLongClickListener {
            tv_successlong.visibility=View.VISIBLE
            true
        }

        bt_back.setOnClickListener {
            finish()
        }
    }

}
