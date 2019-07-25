package com.example.WhateverMyAge

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File

data class RegisterForm(
    var pk : String? = null,
    var id: String?=null,
    var username: String?=null,
    var password1: String?=null,
    var password2: String?=null,
    var email:String?=null,
    var user_photo:String?=null
)

data class LoginForm (
    var user : Body
)


data class Body (
    var username : String,
    var id : String
)

//https://frozen-cove-44670.herokuapp.com/api/v1/registration/
interface Service {

    @GET("/api/v1/{id}")
    fun getReg(                             
        @Path("id") id: Int
       // @Path("pw")pw:String
    ):Call<RegisterForm>

    //회원이름으로 검색 (미구현 0722)
    @GET("/api/v1/users/{username}")
    fun getUser(
        @Path("username")username: String
        // @Path("pw")pw:String
    ):Call<RegisterForm>

    
    
    //회원가입
    @FormUrlEncoded
    @POST("/api/v1/registration/")
    fun postReg(
        @Field("username") username: String?,
        @Field("password1") password1: String?,
        @Field("password2") password2: String?
    ):Call<RegisterForm>


    @FormUrlEncoded
    @GET("/api/v1/login/")
    fun getID (

    ):Call<RegisterForm>

    //로그인
    @FormUrlEncoded
    @POST("/api/v1/login/")
    fun login(
        @Field("username") username: String?,
        @Field("email") email: String?,
        @Field("password") password1: String?

    ):Call<LoginForm>


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

    @FormUrlEncoded
    //@Multipart
    @PUT("/api/v1/{id}")
    fun uploadPic (
        @Path("id") id: String?,
        @Field("user_photo") img : File
        //@Part file : MultipartBody.Part,
        //@Part("File Upload") requestBody : RequestBody
    ):Call<RegisterForm>
}