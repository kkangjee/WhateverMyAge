package com.example.makeref

import android.database.Observable
import retrofit2.Call
import retrofit2.http.*
import java.time.LocalDate


data class RegisterForm(
    var id: String?=null,
    var username: String?=null,
    var password1: String?=null,
    var password2: String?=null,
    var email:String?=null,
    var user_photo:String?=null

)


//https://frozen-cove-44670.herokuapp.com/api/v1/registration/
interface Service {



    @GET("/api/v1/registration/{id}")
    fun getReg(
        @Path("id")id: String
    ):Call<RegisterForm>

    @FormUrlEncoded
    @POST("/api/v1/registration/")
    fun postReg(
        @Field("username") username: String?,
        @Field("password1") password1: String?,
        @Field("password2") password2: String?
    ):Call<RegisterForm>

    @FormUrlEncoded
    @PUT("/api/v1/registration/{id}/")
    fun putReg(
        @Path("id")id: String?,
        @Field("username")username:String?,
        @Field("user_photo")user_photo:String?
    ):Call<RegisterForm>


    @DELETE("/api/v1/registration/{id}/")
    fun deleteReg(
        @Path("id")id: String?
    ):Call<RegisterForm>

}