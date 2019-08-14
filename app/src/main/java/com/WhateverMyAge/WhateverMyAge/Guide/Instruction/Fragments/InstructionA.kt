package com.WhateverMyAge.WhateverMyAge.Guide.Instruction.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.util.Log
import com.WhateverMyAge.WhateverMyAge.R
import com.WhateverMyAge.WhateverMyAge.Guide.Instruction.Instruction
import kotlinx.android.synthetic.main.activity_explanation_fragment.*

class InstructionA : Fragment() {
    var getData = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        val activity = getActivity() as Instruction
        getData = activity.sendData()
        Log.i("data sent", "$getData")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_explanation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i("?", "??????")
        super.onActivityCreated(savedInstanceState)

        val pic: String

        when (getData) {
            1 -> {
                pic = "@drawable/kakao_home"
                explanation.text = "홈 화면의 카카오톡 아이콘을 누르세요."
                piccount.text = "1/6"

            }
            2 -> {
                pic = "@drawable/kakao_main"
                explanation.text = "친구 목록에서 자주 보는 친구를 선택하세요."
                piccount.text = "1/4"

            }
            3 -> {
                pic = "@drawable/kakao_additional"
                explanation.text = "왼쪽 아래의 + 를 누르세요."
                piccount.text = "1/5"
            }
            4 -> {
                pic = "@drawable/youtube_home"
                explanation.text = "홈 화면의 유튜브 아이콘을 누르세요."
                piccount.text = "1/5"
            }
            5 -> {
                pic = "@drawable/youtube_add_playlist"
                explanation.text = "오른쪽의 저장 버튼을 누르세요."
                piccount.text = "1/3"
            }
            6 -> {
                pic = "@drawable/youtube_comment"
                explanation.text = "동영상 밑의 공개 댓글 추가를 누르세요."
                piccount.text = "1/2"
            }
            7 -> {
                pic = "@drawable/contact_home"
                explanation.text = "홈 화면의 연락처 아이콘을 누르세요."
                piccount.text = "1/4"
            }
            else -> pic = "@drawable/kakao_home"
        }
        val res = resources
        val id = res.getIdentifier(pic, "id", context!!.packageName)
        explanationpic.setImageResource(id)
    }
}
