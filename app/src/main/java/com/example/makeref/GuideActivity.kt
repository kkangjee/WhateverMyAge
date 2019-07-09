package com.example.makeref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_guide.*
import kotlinx.android.synthetic.main.activity_main.*

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
            val intent = Intent(this, GuideActivity::class.java)
            startActivity(intent)
        }

    }
}
