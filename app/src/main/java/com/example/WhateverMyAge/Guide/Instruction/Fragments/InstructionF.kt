package com.example.WhateverMyAge.Guide.Instruction.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import sun.invoke.util.VerifyAccess.getPackageName
import android.util.Log
import com.example.WhateverMyAge.R
import com.example.WhateverMyAge.Guide.Instruction.Instruction
import kotlinx.android.synthetic.main.activity_explanation_fragment.*

class InstructionF : Fragment() {
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
                pic = "@drawable/kakao_words_confirm"
                explanation.text = "친구에게 메시지가 전송됐어요."
                piccount.text = "6/6"
            }
            2 -> pic = "@drawable/kakao_before_star"
            3 -> pic = "@drawable/kakao_additional"
            4 -> pic = "@drawable/youtube_main"
            5 -> pic = "@drawable/youtube_videos_in_playlist"
            6 -> pic = "@drawable/youtube_comment_submit"
            7 -> pic = "@drawable/contact_search"
            else -> pic = "@drawable/kakao_home"
        }
        val res = resources
        val id = res.getIdentifier(pic, "id", context!!.packageName)
        explanationpic.setImageResource(id)
    }
}
