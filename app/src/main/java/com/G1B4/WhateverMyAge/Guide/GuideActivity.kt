package com.G1B4.WhateverMyAge.Guide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.G1B4.WhateverMyAge.Guide.Instruction.ChooseInstruction
import com.G1B4.WhateverMyAge.R
import com.G1B4.WhateverMyAge.Guide.Questions.QnA
import com.G1B4.WhateverMyAge.Guide.Settings.SettingActivity
import kotlinx.android.synthetic.main.activity_guide.*
import kotlinx.android.synthetic.main.activity_guide.bt_back

class GuideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)

        bt_setting.setOnClickListener {//setting 화면
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        bt_questions.setOnClickListener {//질문
            val intent = Intent(this, QnA::class.java)
            startActivity(intent)
        }

        bt_explanation.setOnClickListener {//설명서
            val intent = Intent(this, ChooseInstruction::class.java)
            startActivity(intent)
        }

        bt_back.setOnClickListener{
            finish()

        }

    }
}