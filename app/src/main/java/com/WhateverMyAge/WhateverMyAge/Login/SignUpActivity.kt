package com.WhateverMyAge.WhateverMyAge

import android.app.ProgressDialog
import com.WhateverMyAge.WhateverMyAge.Guide.Settings.toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.WhateverMyAge.WhateverMyAge.Main.ConnectServer
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.bt_back
import java.util.regex.Pattern


class SignUpActivity : AppCompatActivity() {


    fun passwordValidator(ID: String, PW: String, PW1: String): String {
        var returnValue = "success"

        val p = Pattern.compile("([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])")
        //val m = p.matcher(PW)

        val p2 = Pattern.compile("(\\w)\\1\\1\\1")
        val m2 = p2.matcher(PW)

        val p3 = Pattern.compile("([\\{\\}\\[\\]\\/?.,;:|\\)*~`!^\\-_+<>@\\#$%&\\\\\\=\\(\\'\\\"])\\1\\1\\1")
        val m3 = p3.matcher(PW)
        if (ID == "" || PW == "" || PW1 == "") {
            returnValue = "빈칸을 채워주세요"
        } else if (PW != PW1) {
            returnValue = "비밀번호가 다릅니다. 확인해주세요."
        } else if (PW.length < 8 || PW.length > 16) {    //자릿수 검증
            returnValue = "비밀번호는 8자 이상, 16자 이하로 구성하세요."
        }
//        else if (!m.find()) {    //정규식 이용한 패턴 체크
//            returnValue = "비밀번호는 영문,숫자,특수문자(!@$%^&* 만 허용)를 조합하여 10~16자로 구성하세요."
//        }
        else if (m2.find() || m3.find()) {    // 동일 문자 4번 입력 시 패턴 체크
            returnValue = "비밀번호에 동일문자를 4번 이상 사용할 수 없습니다."
        } else {
            var chr: Char
            for (i in 0 until ID.length) {
                chr = ID[i] // 입력받은 텍스트에서 문자 하나하나 가져와서 체크


                if (chr >= 0x61.toChar() && chr <= 0x7A.toChar())
                else if(chr >= 0x41.toChar() && chr <= 0x5A.toChar())
                else if(chr >= 0x30.toChar() && chr <= 0x39.toChar())
                else {
                    returnValue = "아이디는 영어와 숫자만 가능합니다."
                }
            }
        }

        return returnValue
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        bt_back.setOnClickListener {
            finish()
        }



        bt_signup.setOnClickListener {
            //회원가입
            val ID = regID.text.toString()
            val PW = regPW.text.toString()
            val PWC = PWConfirm.text.toString()
            val result = passwordValidator(ID, PW, PWC)
            if (result == "success") {
                loading()
                ConnectServer(this).registration(ID, PW, PWC)

            } else {
                toast(result)
            }

        }
    }
    fun loading() {
        //로딩
        android.os.Handler().postDelayed(
            {
                progressDialog = ProgressDialog(this@SignUpActivity)
                progressDialog!!.setIndeterminate(true)
                progressDialog!!.setMessage("잠시만 기다려 주세요")
                progressDialog!!.show()
            }, 0
        )
    }
}