package com.G1B4.WhateverMyAge.Guide.Instruction.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import sun.invoke.util.VerifyAccess.getPackageName
import com.G1B4.WhateverMyAge.R
import com.G1B4.WhateverMyAge.Guide.Instruction.Instruction
import kotlinx.android.synthetic.main.activity_explanation_fragment.*

class InstructionB : Fragment() {
    var getData = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        val activity = getActivity() as Instruction
        getData = activity.sendData()
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
                pic = "@drawable/kakao_main"
                explanation.text = "친구들이 저장되어 있는 곳이에요."
                piccount.text = "2/6"

            }
            2 -> {
                pic = "@drawable/kakao_before_star"
                explanation.text = "오른쪽 위의 별모양을 누르세요."
                piccount.text = "2/4"
            }
            3 -> {
                pic = "@drawable/kakao_pick_from_gallery"
                explanation.text = "앨범을 누르세요."
                piccount.text = "2/5"
            }
            4 -> {
                pic = "@drawable/youtube_main"
                explanation.text = "보고 싶은 영상을 클릭해요.\n오른쪽 위의 버튼으로 검색해요."
                piccount.text = "2/5"
            }
            5 -> {
                pic = "@drawable/youtube_playlists"
                explanation.text = "오른쪽 하단의 '라이브러리'에 추가한 영상이 저장돼요."
                piccount.text = "2/3"
            }
            6 -> {
                pic = "@drawable/youtube_comment_submit"
                explanation.text = "파란 버튼을 누르면 댓글을 남길 수 있어요."
                piccount.text = "2/2"
            }
            7 -> {
                pic = "@drawable/contact_search"
                explanation.text = "돋보기 버튼으로 검색할 수 있어요."
                piccount.text = "2/4"
            }
            else -> pic = "@drawable/kakao_home"
        }
        val res = resources
        val id = res.getIdentifier(pic, "id", context!!.packageName)
        explanationpic.setImageResource(id)
    }
}
