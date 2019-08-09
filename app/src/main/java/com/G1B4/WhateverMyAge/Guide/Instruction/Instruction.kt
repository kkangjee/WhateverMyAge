package com.G1B4.WhateverMyAge.Guide.Instruction

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.G1B4.WhateverMyAge.R
import kotlinx.android.synthetic.main.activity_instruction.*


    var cnt = 1

class Instruction : AppCompatActivity() {
    var which = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        which = intent.getIntExtra("Instruction", -1)
        val s = intent.getIntExtra("Instruction", -1)
        Log.i("for sure?", "$s")
        when (which) {
            1 -> cnt = 6
            2 -> cnt = 4
            3 -> cnt = 5
            4 -> cnt = 5
            5 -> cnt = 3
            6 -> cnt = 2
            7 -> cnt = 4
            else -> cnt = 6
        }

        var adapter = InstructionAdapter(supportFragmentManager)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_instruction)
        instruction.adapter = Instruction@ adapter

        when (s) {
            1 -> instructiontitle.text = "카카오톡 메시지"
            2 -> instructiontitle.text = "친구 빨리 찾기"
            3 -> instructiontitle.text = "사진 보내기"
            4 -> instructiontitle.text = "유튜브 보기"
            5 -> instructiontitle.text = "동영상 저장하기"
            6 -> instructiontitle.text = "댓글 달기"
            7 -> instructiontitle.text = "연락처에 전화하기"
            else -> instructiontitle.text = "스마트폰 가이드"
        }

        val intent = Intent()
        intent.putExtra("WhichExplanation", 1)

        bt_back.setOnClickListener {
            finish()
        }
    }

    fun sendData(): Int {
        return which
    }


}
