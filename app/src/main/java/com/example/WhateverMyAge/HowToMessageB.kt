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
import android.util.Log
import kotlinx.android.synthetic.main.activity_explanation_fragment.*
import java.util.*

class HowToMessageB : Fragment(){
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
                1-> {pic = "@drawable/kakao_main"
                    explanation.text = "친구들이 저장되어 있는 곳이에요."
                    piccount.text = "2/6"

                }
            2-> {pic = "@drawable/kakao_before_star"
                explanation.text = "오른쪽 위의 별모양을 누르세요."
                piccount.text = "2/4"
            }
            3-> {
                pic = "@drawable/kakao_pick_from_gallery"
                explanation.text = "앨범을 누르세요."
                piccount.text = "2/5"
            }
            4->{pic = "@drawable/youtube_main"
                explanation.text = "보고 싶은 영상을 클릭해요.\n오른쪽 위의 버튼으로 검색해요."
                piccount.text = "2/5"
            }
            5->{pic = "@drawable/youtube_playlists"
                explanation.text = "오른쪽 하단의 '라이브러리'에 추가한 영상이 저장돼요."
                piccount.text = "2/3"
            }
            6->{pic = "@drawable/youtube_comment_submit"
                explanation.text = "파란 버튼을 누르면 댓글을 남길 수 있어요."
                piccount.text = "2/2"}
            7->{pic = "@drawable/contact_search"
                explanation.text = "돋보기 버튼으로 검색할 수 있어요."
                piccount.text = "2/4"}
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
