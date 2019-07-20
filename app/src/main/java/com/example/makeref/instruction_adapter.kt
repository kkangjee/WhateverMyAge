package com.example.makeref

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class InstructionAdapter  (fm: FragmentManager): FragmentStatePagerAdapter(fm) {



    override fun getItem(p0: Int): Fragment { //viewpager 보여줄 뷰 객체 반환

        var num = p0%6
        return when(num) {
            0-> HowToMessageA()    //p 값이 0이면 AFragment로 갈래
            1-> HowToMessageB()
            2-> HowToMessageC()
            3->HowToMessageD()
            4->HowToMessageE()
            5->HowToMessageF()
            else -> HowToMessageA()
        }
    }

    override fun getCount() = Int.MAX_VALUE//뷰페이저에서 보여줄 뷰의 개수

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0 -> "맛집"
            else -> {return "여행"}
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {  //viewpager에서 뷰 사라질 때 제거
        super.destroyItem(container, position, `object`)
    }
}