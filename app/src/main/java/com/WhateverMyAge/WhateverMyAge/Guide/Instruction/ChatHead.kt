package com.WhateverMyAge.WhateverMyAge.Guide.Instruction

//package com.example.chatheads

import android.app.Service
import android.content.Context
import android.graphics.PixelFormat
import android.os.IBinder
import android.content.Intent
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.view.WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
import android.widget.ImageView
import com.WhateverMyAge.WhateverMyAge.R
import android.os.Build
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T





class DescForm (val pos : String, val pic : String, val desc : String)


class ChatHead : Service() {
    val descriptions = arrayListOf(
        //메시지
        arrayListOf(
            DescForm("상단 우측 ", "kakao_friend_search", " 로 검색해요"),
            DescForm("원하는 친구를 검색해요", "", ""),
            DescForm("친구를 선택해요", "", ""),
            DescForm("하단 좌측 ", "kakao_friend_bubble", " 를 누르세요"),
            DescForm("원하는 메시지를 입력해요", "", ""),
            DescForm("오른쪽 ", "kakao_image_send", " 로 보내요"),
            DescForm("종료 버튼을 눌러주세요", "", "")
            ),

        //즐겨찾기
        arrayListOf(
            DescForm("상단 우측 ", "kakao_friend_search", " 로 검색해요"),
            DescForm("원하는 친구를 선택해요 ", "", ""),
            DescForm("상단 우측 ", "kakao_fav_unstar", " 를 누르세요"),
            DescForm("별모양이 ", "kakao_fav_star", " 되면 성공입니다")
        ),

        //사진 보내기
        arrayListOf(
            DescForm("하단 두번째 ", "kakao_chatroom_list", " 를 누르세요"),
            DescForm("친구를 선택하세요", "", ""),
            DescForm("하단 좌측 ", "kakao_image_plus", " 를 누르세요"),
            DescForm("", "kakao_image_album", " 를 누르세요"),
            DescForm("사진을 선택하세요", "", ""),
            DescForm("오른쪽 ", "kakao_image_send", " 로 보내요")
        ),

        //동영상 보기
        arrayListOf(
            DescForm("상단 우측 ", "kakao_friend_search", " 로 검색해요"),
            DescForm("동영상을 검색해요", "", ""),
            DescForm("동영상을 선택해요", "", ""),
            DescForm("오른쪽 ", "youtube_subscription", " 로 구독해요")
        )
    )

    var exp = 0
    var page = 0

    private var windowManager: WindowManager? = null
    private var chatheadView: View? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        exp = intent.extras!!.get("which") as Int
        Log.i("받았음", Integer.toString(exp) + "를 받아요")

        val descPic = chatheadView!!.findViewById<ImageView>(R.id.chathead_image)

        //사진 올리기
        val res = resources
        descPic.setImageResource(res.getIdentifier(descriptions[exp][page].pic, "drawable", packageName))
        Log.i("사진", "" + page + "번째 " + descriptions[exp][page].pic + "")

        //내용 올리기
        val descCon = chatheadView!!.findViewById<TextView>(R.id.chathead_desc)
        descCon.text = descriptions[exp][page].desc
        Log.i("내용", "" + page + "번째 " + descriptions[exp][page].desc + "")

        val descPos = chatheadView!!.findViewById<TextView>(R.id.chathead_pos)
        descPos.text = descriptions[exp][page].pos
        Log.i("위치", "" + page + "번째 " + descriptions[exp][page].pos + "")

        //닫기 리스너
        val close = chatheadView!!.findViewById<Button>(R.id.bt_finish)
        Log.i("www", Integer.toString(exp))
        close.setOnClickListener{
            stopSelf()
        }

        //이전 리스너
        val prev = chatheadView!!.findViewById<Button>(R.id.bt_prev)
        prev.setOnClickListener {
            Log.i("사진", "" + page + "번째 " + descriptions[exp][page].pic + "")
            Log.i("사진", "" + page + "번째 " + descriptions[exp][page].desc + "")
            if (page > 0) {
                page--
                descPic.setImageResource(res.getIdentifier(descriptions[exp][page].pic, "drawable", packageName))
                descCon.text = descriptions[exp][page].desc
                descPos.text = descriptions[exp][page].pos
            }
        }

        //다음 리스너
        val next = chatheadView!!.findViewById<Button>(R.id.bt_next)
        next.setOnClickListener {
            if (page < descriptions[exp].lastIndex) {
                page++
                descPic.setImageResource(res.getIdentifier(descriptions[exp][page].pic, "drawable", packageName))
                descCon.text = descriptions[exp][page].desc
                descPos.text = descriptions[exp][page].pos
            }
        }

        return exp
    }

    override fun onCreate() {
        super.onCreate()

        val LAYOUT_FLAG: Int
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            LAYOUT_FLAG = WindowManager.LayoutParams.TYPE_PHONE
        }

        chatheadView = LayoutInflater.from(this).inflate(R.layout.bubble, null)
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            //WindowManager.LayoutParams.TYPE_PHONE,
            //WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            LAYOUT_FLAG,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
//            WindowManager.LayoutParams.WRAP_CONTENT,
//            WindowManager.LayoutParams.WRAP_CONTENT,
//            LAYOUT_FLAG,
//            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//            PixelFormat.TRANSLUCENT
        )
//        val params = WindowManager.LayoutParams(
//            WindowManager.LayoutParams.WRAP_CONTENT,
//            WindowManager.LayoutParams.WRAP_CONTENT,
//            LAYOUT_FLAG,
//            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
//            PixelFormat.TRANSLUCENT
//        )

        params.gravity = Gravity.TOP or Gravity.LEFT
        params.x = 0
        params.y = 100

        windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager!!.addView(chatheadView, params)

        val chatheadBubble = chatheadView!!.findViewById<RelativeLayout>(R.id.chathead_bubble)
        chatheadBubble.setOnTouchListener(
            object : View.OnTouchListener {
                private var initialX: Int = 0
                private var initialY: Int = 0
                private var touchX: Float = 0.toFloat()
                private var touchY: Float = 0.toFloat()
                private var lastAction: Int = 0

                override fun onTouch(v: View, event: MotionEvent): Boolean {
                    if (event.action == MotionEvent.ACTION_DOWN) {
                        initialX = params.x
                        initialY = params.y

                        touchX = event.rawX
                        touchY = event.rawY

                        lastAction = event.action

                        return true
                    }

                    if (event.action == MotionEvent.ACTION_UP) {
                        if (lastAction == MotionEvent.ACTION_DOWN) {
                            val button = Button(this@ChatHead)
                            button.text = "Close"

                            val layout = chatheadView!!.findViewById<RelativeLayout>(R.id.chathead_bubble)
                            layout.addView(button)

                            button.setOnClickListener { stopSelf() }
                        }

                        lastAction = event.action
                        return true
                    }

                    if (event.action == MotionEvent.ACTION_MOVE) {
                        params.x = initialX + (event.rawX - touchX).toInt()
                        params.y = initialY + (event.rawY - touchY).toInt()

                        windowManager!!.updateViewLayout(chatheadView, params)
                        lastAction = event.action
                        return true
                    }

                    return false
                }
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()

        if (chatheadView != null) {
            windowManager!!.removeView(chatheadView)
        }
    }
}