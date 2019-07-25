package com.example.WhateverMyAge.Guide.Instruction


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.WhateverMyAge.R
import kotlinx.android.synthetic.main.activity_explanation.*
import kotlinx.android.synthetic.main.activity_love.bt_back


class Explanation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explanation)

        bt_howtomessage.setOnClickListener {
            val intent = Intent(this, Instruction::class.java)
            intent.putExtra("Instruction",1)
            startActivity(intent)
        }
        bt_howtofriend.setOnClickListener {
            val intent = Intent(this, Instruction::class.java)
            intent.putExtra("Instruction",2)
            startActivity(intent)
        }
        bt_howtopic.setOnClickListener {
            val intent = Intent(this, Instruction::class.java)
            intent.putExtra("Instruction",3)
            startActivity(intent)
        }

        bt_howtoplayyoutube.setOnClickListener {
            val intent = Intent(this, Instruction::class.java)
            intent.putExtra("Instruction",4)
            startActivity(intent)
        }
        bt_howtosaveyoutube.setOnClickListener {
            val intent = Intent(this, Instruction::class.java)
            intent.putExtra("Instruction",5)
            startActivity(intent)
        }
        bt_howtoyoutubecomment.setOnClickListener {
            val intent = Intent(this, Instruction::class.java)
            intent.putExtra("Instruction",6)
            startActivity(intent)
        }
        bt_howtocontact.setOnClickListener {
            val intent = Intent(this, Instruction::class.java)
            intent.putExtra("Instruction",7)
            startActivity(intent)
        }


        bt_back.setOnClickListener{
            finish()

        }
    }




}
