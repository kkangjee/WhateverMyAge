package com.WhateverMyAge.WhateverMyAge.Main

import java.util.*

class Quotes (val Quote : String, val QuotedFrom : String){

}val quotelist = arrayListOf(
    Quotes("죽고자 하면 살 것이고 살고자 하면 죽을 것이다.", "-이순신"),
    Quotes("복수는 차갑게 식혀서 먹을 때가 가장 맛있는 음식과도 같다.", "-프랑스 속담"),
    Quotes("사진이 진실이라면 영화는 초당 24번의 진실이다", "-장 뤽 고다르"),
    Quotes("카메라는 불행한 일들과 그들이 잊혀지는 것에 대한 무기이다", "-빔 벤더스"),
    Quotes("영화란 지루한 부분이 커트된 인생이다.", "-알프레드 히치콕")
)
val random = Random()
var num = random.nextInt(5)