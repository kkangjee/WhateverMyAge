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
import com.example.WhateverMyAge.Main.ImageURL
import com.example.WhateverMyAge.Main.random
import com.example.WhateverMyAge.R
import kotlinx.android.synthetic.main.activity_travel_fragment.*

class TravelFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.activity_travel_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = TravelAPI(false)
        val apiList = api.getAPI(lng, lat)
        //iv_TravelPic.setImageBitmap(bitmap)

        // travelspot.text = api.api[0].add1

        var last = apiList.lastIndex
        val imageURL = ImageURL()

        if (last > 0) {
            var rand = random.nextInt(last)
            val walk=apiList[rand].dist.toInt()
            if (apiList[rand].image != null) {
                val bitmap = imageURL.setImageURL(apiList[rand].image)
                iv_TravelPic1.setImageBitmap(bitmap)
            }

            travelspot1.text =
                "여행지 : " + apiList[rand]?.title + "\n주소 : " + apiList[rand]?.add1 + "\n거리 : " + walk*3 + "걸음"
            if (rand != last)
                apiList[rand] = apiList[last]
            last = last - 1
        } else
            travelspot1.text = "주변에 여행지가 없어요 ~ ㅠㅠ"
        if (last > 0) {
            var rand2 = random.nextInt(last)
            val walk2=apiList[rand2].dist.toInt()
            if (apiList[rand2].image != null) {
                val bitmap = imageURL.setImageURL(apiList[rand2].image)
                iv_TravelPic2.setImageBitmap(bitmap)
            }
            travelspot2.text =
                "여행지 : " + apiList[rand2]?.title + "\n주소 : " + apiList[rand2]?.add1 + "\n거리 : " + walk2*3 + "걸음"
            if (rand2 != last)
                apiList[rand2] = apiList[last]
            last = last - 1
        }
        if (last > 0) {
            var rand3 = random.nextInt(last)
            val walk3=apiList[rand3].dist.toInt()
            if (apiList[rand3].image != null) {
                val bitmap = imageURL.setImageURL(apiList[rand3].image)
                iv_TravelPic3.setImageBitmap(bitmap)
            }
            travelspot3.text =
                "여행지 : " + apiList[rand3]?.title + "\n주소 : " + apiList[rand3]?.add1 + "\n거리 : " + walk3*3 + "걸음"
            if (rand3 != last)
                apiList[rand3] = apiList[last]
            last = last - 1
        }
        if (last > 0) {
            var rand4 = random.nextInt(last)
            val walk4=apiList[rand4].dist.toInt()
            if (apiList[rand4].image != null) {
                val bitmap = imageURL.setImageURL(apiList[rand4].image)
                iv_TravelPic4.setImageBitmap(bitmap)
            }
            travelspot4.text =
                "여행지 : " + apiList[rand4]?.title + "\n주소 : " + apiList[rand4]?.add1 + "\n거리 : " + walk4*3 + "걸음"
            if (rand4 != last)
                apiList[rand4] = apiList[last]
            last = last - 1
        }
        if (last > 0) {
            var rand5 = random.nextInt(last)
            val walk5=apiList[rand5].dist.toInt()
            if (apiList[rand5].image != null) {
                val bitmap =imageURL.setImageURL(apiList[rand5].image)
                iv_TravelPic5.setImageBitmap(bitmap)
            }
            travelspot5.text =
                "여행지 : " + apiList[rand5]?.title + "\n주소 : " + apiList[rand5]?.add1 + "\n거리 : " + walk5*3 + "걸음"
            if (rand5 != last)
                apiList[rand5] = apiList[last]
            last = last - 1
        }
        //tvFragmentMain.setImageResource(R.drawable.avengers)     //text 대신 tv~~.ImageView = drawable.. 하면 됨
        //View v = getView()
    }
}