package com.example.makeref

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_food_fragment.*
import kotlinx.android.synthetic.main.activity_travel_and_food.*

class TravelAndFood : AppCompatActivity() {

    private val adapter by lazy { TravelAndFoodAdapter(supportFragmentManager) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_and_food)
        travelandfood.adapter = TravelAndFood@adapter

        travelorfood.setupWithViewPager(travelandfood)



    }



}
