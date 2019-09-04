package com.WhateverMyAge.WhateverMyAge.Practice.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import sun.invoke.util.VerifyAccess.getPackageName
import com.WhateverMyAge.WhateverMyAge.R
import com.WhateverMyAge.WhateverMyAge.Practice.PageSlide
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import kotlinx.android.synthetic.main.activity_explanation_fragment.*

class Page2 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_explanation_fragment, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val pic = "puppy"
        val res = resources
        val id = res.getIdentifier(pic, "drawable", context!!.packageName)
        explanationpic.setImageResource(id)
        explanation.text = "종이를 넘기는 것과 같아요."

        val gifImage = GlideDrawableImageViewTarget(gif)
        val slide = res.getIdentifier("howtoslide", "drawable", context!!.packageName)

        Glide.with(activity).load(slide).into(gifImage)
    }
}
