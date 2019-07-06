package com.example.makeref

import android.content.Context
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_setting.*
import android.widget.SeekBar
import android.view.WindowManager





class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        //오디오 관리자 서비스 받기
        val audioManager:AudioManager = getSystemService(AUDIO_SERVICE) as AudioManager


        bt_volup.setOnClickListener {
            val maxVolume = audioManager.mediaMaxVolume
            val upIndex = audioManager.mediaCurrentVolume +1

            audioManager.setMediaVolume(upIndex)

            toast("지금 소리 크기는 ${audioManager.mediaCurrentVolume} 입니다.")
        }

        bt_voldown.setOnClickListener {
            val downIndex = audioManager.mediaCurrentVolume -1

            audioManager.setMediaVolume(downIndex)

            toast("지금 소리 크기는 ${audioManager.mediaCurrentVolume} 입니다.")
        }

        /*seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {//밝기조절
            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // 메소드 이름대로 사용자가 SeekBar를 터치했을때 실행됩니다
            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // 메소드 이름대로 사용자가 SeekBar를 손에서 땠을때 실행됩니다
            }
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                // 메소드 이름대로 사용자가 SeekBar를 움직일때 실행됩니다
                // 주로 사용되는 메소드 입니다
                toast("\"밝기 수준 : \" + progress")

                if (progress < 10) {

                    progress = 10

                    seekBar.setProgress(progress)

                }

                val params = window.attributes
                params.screenBrightness = progress as Float / 100
                window.attributes = params
            }

        })*/
    }


}

fun AudioManager.setMediaVolume(volumeIndex:Int){
    this.setStreamVolume(
        AudioManager.STREAM_MUSIC,//type 설정
        volumeIndex,//볼륨 크기
        AudioManager.FLAG_SHOW_UI //보여주는 방식

    )
}
val AudioManager.mediaMaxVolume:Int
    get() = this.getStreamMaxVolume(AudioManager.STREAM_MUSIC)

val AudioManager.mediaCurrentVolume:Int
    get() = this.getStreamVolume(AudioManager.STREAM_MUSIC)

fun Context.toast(message:String){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
}
