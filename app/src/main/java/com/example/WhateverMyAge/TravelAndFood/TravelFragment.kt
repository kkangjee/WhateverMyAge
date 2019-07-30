package com.example.WhateverMyAge.TravelAndFood

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.WhateverMyAge.Love.lat
import com.example.WhateverMyAge.Love.lng
import com.example.WhateverMyAge.Main.random
import com.example.WhateverMyAge.R
import kotlinx.android.synthetic.main.activity_travel_fragment.*

class TravelFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.activity_travel_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = TravelAPI()
        val apiList = api.getAPI(lat, lng)
        //iv_TravelPic.setImageBitmap(bitmap)

        // travelspot.text = api.api[0].add1

        var last = apiList.lastIndex

        var rand = random.nextInt(10)

        if (apiList[rand] == null) {
            travelspot.text = "주변에 여행지가 없어요 ~ ㅠㅠ"
        } else {
            val bitmap = api.setImageURL(apiList[rand].image)
            iv_TravelPic.setImageBitmap(bitmap)

            travelspot.text =
                "여행지 : " + apiList[rand]?.title + "\n주소 : " + apiList[rand]?.add1 + "\n거리 : " + apiList[rand]?.dist
        }
        //tvFragmentMain.setImageResource(R.drawable.avengers)     //text 대신 tv~~.ImageView = drawable.. 하면 됨
        //View v = getView()
    }
}