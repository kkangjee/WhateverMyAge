package com.example.makeref

import android.database.Observable
import retrofit2.Call
import retrofit2.http.*
import java.time.LocalDate



data class PostSnsData(
    var pr: String,
    var b_contents: String,
    var b_created: String
)


interface Service {

    @FormUrlEncoded
    @POST("/api/v1/blog/?format=api")
    fun postSNS(
        @Field("pr") pr: String?,
        @Field("b_contents") b_contents: String?,
        @Field("b_created") b_created: String?):Call<PostSnsData>

    @GET("/api/v1/blog/{pr}")
    fun getPr(@Path("pr")pr: String):Call<PostSnsData>

}