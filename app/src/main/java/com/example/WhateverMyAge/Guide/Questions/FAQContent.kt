package com.example.WhateverMyAge.Guide.Questions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.WhateverMyAge.R

class FAQContent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faqcontent)

        val num = intent.getIntExtra("FAQ", -1)






    }
}
