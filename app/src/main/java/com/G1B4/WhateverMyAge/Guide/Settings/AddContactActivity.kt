package com.G1B4.WhateverMyAge.Guide.Settings

import android.app.AlertDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import com.G1B4.WhateverMyAge.R
import kotlinx.android.synthetic.main.activity_addcontact.*

class AddContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addcontact)

        bt_add.setOnClickListener {
            //setting 화면
            var name: String = et_name.text.toString()
            var phone: String = et_phone.text.toString()
            addContact(name, phone)
        }
    }

    fun launchIntent(intent: Intent?) {
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
            try {
                //activity.startActivity(intent);
                this.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle(R.string.app_name)
                builder.setMessage("set")
                builder.setPositiveButton("ok", null)
                builder.show()
            }

        } else {
            toast("추가 안됨")
        }
    }

    fun addContact(
        names: String?, mobile: String) {
        val intent = Intent(Contacts.Intents.Insert.ACTION, Contacts.People.CONTENT_URI)

        intent.putExtra(Contacts.Intents.Insert.NAME, names)
        intent.putExtra(Contacts.Intents.Insert.PHONE, mobile)
        intent.putExtra(Contacts.Intents.Insert.PHONE_TYPE, Contacts.PhonesColumns.TYPE_MOBILE)
        launchIntent(intent)
    }
}
