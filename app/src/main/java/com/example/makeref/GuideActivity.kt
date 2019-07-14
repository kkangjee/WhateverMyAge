package com.example.makeref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_guide.*
<<<<<<< HEAD
=======
import kotlinx.android.synthetic.main.activity_main.*
>>>>>>> f9e2692a19cc74eb9848a0574061ea26c5c60c13

class GuideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guide)

        bt_setting.setOnClickListener {//setting 화면
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        bt_questions.setOnClickListener {//질문
<<<<<<< HEAD
            val intent = Intent(this, QnaActivity::class.java)
=======
            val intent = Intent(this, QnA::class.java)
>>>>>>> f9e2692a19cc74eb9848a0574061ea26c5c60c13
            startActivity(intent)
        }

        bt_explanation.setOnClickListener {//설명서
            val intent = Intent(this, Explanation::class.java)
            startActivity(intent)
        }

    }
}
