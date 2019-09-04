package com.WhateverMyAge.WhateverMyAge.Practice

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.WhateverMyAge.WhateverMyAge.Practice.Fragments.Page1
import com.WhateverMyAge.WhateverMyAge.Practice.Fragments.Page2
import com.WhateverMyAge.WhateverMyAge.Practice.Fragments.Page3

class InstructionAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment { //viewpager 보여줄 뷰 객체 반환
        val num = p0
        return when (num) {
            0 -> Page1()    //p 값이 0이면 AFragment로 갈래
            1 -> Page2()
            2 -> Page3()
            else -> Page1()
        }
    }

    override fun getCount() = 3//뷰페이저에서 보여줄 뷰의 개수

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "맛집"
            else -> {
                return "여행"
            }
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {  //viewpager에서 뷰 사라질 때 제거
        super.destroyItem(container, position, `object`)
    }
}