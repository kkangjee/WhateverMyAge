package com.example.makeref

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_food_fragment.*
import kotlinx.android.synthetic.main.activity_love_write_article.*
import kotlinx.android.synthetic.main.activity_travel_and_food.*
import kotlinx.android.synthetic.main.activity_travel_and_food.bt_back

class TravelAndFood : AppCompatActivity() {
    private val adapter = TravelAndFoodAdapter(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        val which = intent.getIntExtra("which", -1)

        var arguments = Bundle()
        arguments.putInt("Which", which)
        val myfragment = Fragment()
        myfragment.setArguments(arguments)
        supportFragmentManager.beginTransaction().replace(R.id.travelandfood, myfragment)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_travel_and_food)
        travelandfood.adapter = TravelAndFood@adapter
        val intent = Intent()
        intent.putExtra("WhichExplanation", 1)

        travelorfood.setupWithViewPager(travelandfood)

        bt_back.setOnClickListener{
            finish()
        }
    }
}
