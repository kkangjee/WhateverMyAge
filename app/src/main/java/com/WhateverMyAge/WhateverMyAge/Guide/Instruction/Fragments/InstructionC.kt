package com.WhateverMyAge.WhateverMyAge.Guide.Instruction.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import sun.invoke.util.VerifyAccess.getPackageName
import android.util.Log
import com.WhateverMyAge.WhateverMyAge.R
import com.WhateverMyAge.WhateverMyAge.Guide.Instruction.Instruction
import kotlinx.android.synthetic.main.activity_explanation_fragment.*

class InstructionC : Fragment() {
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
        super.onActivityCreated(savedInstanceState)

        val pic: String



        when (getData) {
            1 -> {
                pic = "@drawable/kakao_chat_rooms"
                explanation.text = "대화를 나누고 싶은 친구를 선택해요."
                piccount.text = "3/6"
            }
            2 -> {
                pic = "@drawable/kakao_after_star"
                explanation.text = "노란색이 되면 성공이에요."
                piccount.text = "3/4"
            }
            3 -> {
                pic = "@drawable/kakao_mini_gallery"
                explanation.text = "왼쪽, 오른쪽으로 넘겨가며 보내고 싶은 사진을 선택해요."
                piccount.text = "3/5"
            }
            4 -> {
                pic = "@drawable/youtube_channel"
                explanation.text = "동영상을 올린 사람을 확인할 수도 있어요."
                piccount.text = "3/5"
            }
            5 -> {
                pic = "@drawable/youtube_videos_in_playlist"
                explanation.text = "재생목록에서 영상을 확인할 수 있어요."
                piccount.text = "3/3"
            }
            6 -> pic = "@drawable/youtube_comment"
            7 -> {
                pic = "@drawable/contact_searched"
                explanation.text = "동생을 검색했어요."
                piccount.text = "3/4"
            }
            else -> pic = "@drawable/kakao_home"
        }
        val res = resources
        val id = res.getIdentifier(pic, "id", context!!.packageName)
        explanationpic.setImageResource(id)
    }

}
