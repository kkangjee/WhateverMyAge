package com.example.WhateverMyAge.TravelAndFood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.WhateverMyAge.R

class TravelFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.activity_travel_fragment, container,false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //tvFragmentMain.setImageResource(R.drawable.avengers)     //text 대신 tv~~.ImageView = drawable.. 하면 됨
        //View v = getView()
    }
}
