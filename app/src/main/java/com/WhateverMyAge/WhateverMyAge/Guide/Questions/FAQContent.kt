package com.WhateverMyAge.WhateverMyAge.Guide.Questions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.WhateverMyAge.WhateverMyAge.R
import kotlinx.android.synthetic.main.activity_faqcontent.*

class FAQContent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqcontent)

        val num = intent.getStringArrayListExtra("FAQ")

        faq_title.text = num[1]

        when (num[0].toInt()) {
            0 -> faq_content.text = "저희 앱의 첫 화면에서 ‘설정’버튼을 누른 후 ‘밝기를 높게’ 라는 버튼을 누르면 화면이 밝아져요. 마찬가지로 ‘화면을 어둡게’라는 버튼을 누르면 화면이 어두워져요."
            1 -> faq_content.text = "저희 앱의 첫 화면에서 ‘설정’버튼을 누른 후 ‘벨소리를 크게’라는 버튼을 한번씩 누를 때마다 벨소리 크기가 한단계씩 커져요. 마찬가지로 ‘벨소리를 작게’라는 버튼을 한번씩 누를 때마다 벨소리 크기가 한단계씩 작아져요. 버튼을 누를 때마다 핸드폰 아래 쪽에 지금 크기가 얼마나인지 네모난 상자안에 나타내진답니다."
            2 -> faq_content.text = "저희 앱의 첫 화면에서 ‘설정’버튼을 누른 후 ‘동영상 소리를 크게’라는 버튼을 한번씩 누를 때마다 동영상과 음악소리 크기가 한단계씩 커져요. 마찬가지로 ‘동영상 소리를 작게’라는 버튼을 한번씩 누를 때마다 동영상과 음악 크기가 한단계씩 작아져요. 버튼을 누를 때마다 핸드폰 아래 쪽에 지금 크기가 얼마나인지 네모난 상자안에 나타내진답니다."
            3-> faq_content.text = "저희 앱의 첫 화면에서 ‘설정’버튼을 누른 후 ‘전화번호 등록’이라고 써져 있는 버튼을 누르고 저장하고 싶은 이름과 번호를 입력하세요.‘연락처에 번호 등록하기' 라는 버튼을 누르고 다음 화면에서 저장을 눌러요."
            4 -> faq_content.text = "저희 앱의 첫 화면에서 ‘설정’ 버튼을 누르면 버튼들이 나와요. 그중에 ‘스마트폰 설명서’라는 버튼을 누르면 카카오톡과 유튜브의 사용법에 대한 설명서를 볼 수 있어요."
        }


        bt_back.setOnClickListener {
            finish()
        }


    }
}
