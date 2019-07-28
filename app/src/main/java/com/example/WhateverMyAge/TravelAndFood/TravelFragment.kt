package com.example.WhateverMyAge.TravelAndFood

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.WhateverMyAge.Main.random
import com.example.WhateverMyAge.R
import kotlinx.android.synthetic.main.activity_travel_fragment.*

class TravelFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.activity_travel_fragment, container,false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = TravelAPI()
        val apiList = api.getAPI()
        //iv_TravelPic.setImageBitmap(bitmap)

       // travelspot.text = api.api[0].add1

        var last = apiList.lastIndex

        var rand = random.nextInt(10)
        val bitmap = api.setImageURL(apiList[rand].image)
        iv_TravelPic.setImageBitmap(bitmap)
        //tvFragmentMain.setImageResource(R.drawable.avengers)     //text 대신 tv~~.ImageView = drawable.. 하면 됨
        //View v = getView()
    }
}