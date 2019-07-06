package com.example.makeref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_setting.setOnClickListener {//setting 화면
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        bt_love.setOnClickListener {//사랑방 화면
            val intent = Intent(this, LoveActivity::class.java)
            startActivity(intent)
        }
        bt_login.setOnClickListener {//로그인 화면
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        bt_notyet.setOnClickListener{//기능 구현X
            Toast.makeText(this,"미구현", Toast.LENGTH_SHORT).show()
            //val intent = Intent(this, NotyetActivity::class.java)
            //startActivity(intent)
        }
    }


}
