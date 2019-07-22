package com.example.WhateverMyAge

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_food_fragment.*
//import sun.invoke.util.VerifyAccess.getPackageName
import android.R.attr.name
import android.graphics.drawable.GradientDrawable
import java.util.*

class Menu (val pic : String, val menu : String, val effect : String)

class FoodFragment : Fragment(){
    var menuList = arrayListOf(
        Menu("@/drawable/samgyetang", "삼계탕", "면역력"),
        Menu("@/drawable/guljeon","굴전", "빈혈과 혈압, 다이어트"),
        Menu("@/drawable/oinaegguk","미역오이냉국", "뼈"),
        Menu("@/drawable/sundaeguk","순대국", "눈"),
        Menu("@/drawable/oibokkeum","오이볶음", "피부건강"),
        Menu("@/drawable/sogalbijjim","소갈비찜", "피와 스태미너"),
        Menu("@/drawable/doinjangjjigae","된장찌개", "항암과 간, 골다공증"),
        Menu("@/drawable/gajibokkeum","가지볶음", "피부와 피로"),
        Menu("@/drawable/yeonpotang","연포탕", "피로회복"),
        Menu("@/drawable/hunjeori","훈제요리", "신장과 기력보충"),
        Menu("@/drawable/daeha","대하찜", "해독작용, 다이어트"),
        Menu("@/drawable/godeungeogui","고등어 구이", "치매, 혈관질환"),
        Menu("@/drawable/chueotang","추어탕", "고혈압"),
        Menu("@/drawable/dongtaejjigae","동태찌개", "피부 노화"),
        Menu("@/drawable/golbaengimuchim","골뱅이 무침", "눈과 피부, 스태미너"),
        Menu("@/drawable/juksunmuchim","죽순 무침", "고혈압")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.activity_food_fragment, container,false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val rightNow = Calendar.getInstance()
        val currentHourIn24Format = rightNow.get(Calendar.HOUR_OF_DAY) // return the hour in 24 hrs format (ranging from 0-23)
        if (currentHourIn24Format < 14)
            dayornight.text = "점심"
        else
            dayornight.text = "저녁"

        var num = random.nextInt(menuList.size)

        effect.text = menuList[num].effect
        food.text = menuList[num].menu

        val drawable = context?.getDrawable(R.drawable.rounding)

        val pic  = menuList[num].pic
        foodRecommend.setBackground(drawable)
        foodRecommend.setClipToOutline(true)

        val res = resources
        val id = res.getIdentifier(pic, "id", context!!.packageName)
        foodRecommend.setImageResource(id)

        restaurantRecommend.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.dalbodre.kr/"))
            startActivity(intent)
        }
        //tvFragmentMain.setImageResource(R.drawable.avengers)     //text 대신 tv~~.ImageView = drawable.. 하면 됨
        //View v = getView()
    }




}
