package com.WhateverMyAge.WhateverMyAge.Practice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.WhateverMyAge.WhateverMyAge.R
import kotlinx.android.synthetic.main.activity_pageslide.*

class PageSlide : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var adapter =
            InstructionAdapter(supportFragmentManager)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pageslide)
        instruction.adapter = PageSlide@ adapter
        bt_back.setOnClickListener {
            finish()
        }
    }
}
