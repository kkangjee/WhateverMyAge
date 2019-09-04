package com.WhateverMyAge.WhateverMyAge.Practice.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.util.Log
import com.WhateverMyAge.WhateverMyAge.R
import com.WhateverMyAge.WhateverMyAge.Practice.PageSlide
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import kotlinx.android.synthetic.main.activity_explanation_fragment.*

class Page1 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_explanation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pic = "flower"
        val res = resources
        val id = res.getIdentifier(pic, "drawable", context!!.packageName)
        explanationpic.setImageResource(id)
        explanation.text = "오른쪽에서 왼쪽으로\n손가락으로 화면을 쓸어보세요."
        val gifImage = GlideDrawableImageViewTarget(gif)
        val slide = res.getIdentifier("howtoslide", "drawable", context!!.packageName)

        Glide.with(activity).load(slide).into(gifImage)
    }
}
