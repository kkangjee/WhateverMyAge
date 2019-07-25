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

class HowToMessageD : Fragment(){
    var getData = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        val activity  = getActivity() as Instruction
        getData = activity.sendData()
        Log.i("data sent", "$getData")
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
     /*   val bundle = this.getArguments()

        var value1 = bundle?.getInt("Which", -1)
        Log.i("which fragment?", "$value1")
*/
        getBundle()
        return inflater.inflate(R.layout.activity_explanation_fragment, container,false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.i("?", "??????")
        super.onActivityCreated(savedInstanceState)

        val pic :String
        when (getData) {
                1-> {pic = "@drawable/kakao_in_chat_room"
                    explanation.text = "밑의 하얀 부분을 누르면 키보드가 나와요."
                    piccount.text = "4/6"
                }
            2-> {pic = "@drawable/kakao_main_star"
                explanation.text = "이제 찾지 않아도 맨 위에 친구가 있어요."
                piccount.text = "4/4"
            }
            3->{pic = "@drawable/kakao_whole_gallery"
                explanation.text = "보내고 싶은 사진을 찾기 어려우면 전체보기를 눌러 찾아요."
                piccount.text = "4/5"}
            4->{pic = "@drawable/youtube_channel_video"
                explanation.text = "위의 오른쪽의 동영상을 누르세요."
                piccount.text = "4/5"}
            5->pic = "@drawable/youtube_add_playlist"
            6->pic = "@drawable/youtube_comment_submit"
            7->{pic = "@drawable/contact_call"
                explanation.text = "전화기 모양 버튼을 눌러 전화해요."
                piccount.text = "4/4"
            }
            else-> pic = "@drawable/kakao_home"
        }
        val res = resources
        val id = res.getIdentifier(pic, "id", context!!.packageName)
        explanationpic.setImageResource(id)

        //tvFragmentMain.setImageResource(R.drawable.avengers)     //text 대신 tv~~.ImageView = drawable.. 하면 됨
        //View v = getView()
    }

    private fun getBundle() {
        var bundle = this.getArguments()
        var value = 1
        if (null != bundle) {
           value = bundle?.getInt("Which", -1)
        }
        Log.i("which fragment?", "$value")
    }



}
