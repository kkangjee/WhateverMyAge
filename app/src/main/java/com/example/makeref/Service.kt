package com.example.makeref

import android.database.Observable
import retrofit2.Call
import retrofit2.http.*
import java.time.LocalDate



data class PostRegistrationForm(
    var username: String,
    var email: String,
    var password1: String,
    var password2: String
)

//https://frozen-cove-44670.herokuapp.com/api/v1/registration/
interface Service {

    @FormUrlEncoded
    @POST("/api/v1/registration")
    fun postRegistration(
        @Field("username") username: String?,
        @Field("email") email: String?,
        @Field("password1") password1: String?,
        @Field("password2") password2: String?):Call<PostRegistrationForm>

    @GET("/api/v1/registration/{id}")
    fun getRegistration(@Path("id")pr: String):Call<PostRegistrationForm>



    //"https://frozen-cove-44670.herokuapp.com/media/maxresdefault.jpg"
}