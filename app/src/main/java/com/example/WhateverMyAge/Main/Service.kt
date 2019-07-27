package com.example.WhateverMyAge.Main

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

data class PostsList (
    var test : List<PostsForm>
)

data class PostsForm(
//    var id : String? = null,
//    var author_name : String? = null,
//      var opop : Array<Post?> = arrayOfNulls<Post?>(100)
    var title : String,
    var id : Int
//    var like : Int = 0,
//    var content : String? = null,
//    var photo : String? = null,
//    var gps : String? = null,
//    var created : String? = null,
//    var updated : String? = null,
//    var url : String? = null
)

data class LoginForm (
    var user : Body
)

data class Body (
    var username : String,
    var id : String
)

data class Post (
    var title : String,
    var id : Int
)

//https://frozen-cove-44670.herokuapp.com/api/v1/registration/
interface Service {

    //내 정보 받아오기
    @GET("/api/v1/users/{id}")
    fun getReg(                             
       @Path("id") id: Int
       // @Path("pw")pw:String
    ):Call<RegisterForm>

    //회원가입
    @FormUrlEncoded
    @POST("/api/v1/users/registration")
    fun postReg(
        @Field("username") username: String?,
        @Field("password1") password1: String?,
        @Field("password2") password2: String?
    ):Call<RegisterForm>

    //로그인
    @FormUrlEncoded
    @POST("/api/v1/users/login/")
    fun login(
        @Field("username") username: String?,
        @Field("email") email: String?,
        @Field("password") password1: String?
    ):Call<LoginForm>

    //@FormUrlEncoded
    @GET("/api/v1/users/logout")
    fun logout (
    ):Call<RegisterForm>


    //회원정보 수정
    @FormUrlEncoded
    @PUT("/api/v1/registration/{id}/")
    fun putReg(
        @Path("id")id: String?,
        @Field("username")username:String?,
        @Field("user_photo")user_photo:String?
    ):Call<RegisterForm>

    //회원 탈퇴
    @DELETE("/api/v1/users/{id}")
    fun deleteReg(
        @Path("id")id: String?
    ):Call<RegisterForm>

    //사진 업로드
    //@FormUrlEncoded
//    @Multipart
//    @PUT("/api/v1/a")
//    fun uploadPic (
//        //@Path("id") id: String?,
//       // @Field("user_photo") img : File
//        @Part file : MultipartBody.Part
//       // @Part("File Upload") requestBody : RequestBody
//    ):Call<RegisterForm>

    @FormUrlEncoded
    @POST("/api/v1/blog/postings/")
    fun postBlog (
//        @Field("id") id : Int,
//        @Field("author_username") author_username : String?,
        @Field("title") title : String?
//        @Field("like") like : Int,
//        @Field("content") content : String?,
//        @Field("photo") photo : File?,
//        @Field("gps") gps : String?
    ):Call<RegisterForm>

    @GET("/api/v1/blog/postings/")
    fun showPost (
        //@Path("id") id : Int
    ):Call<List<PostsForm>>

    @FormUrlEncoded
    @PUT("/api/v1/blog/postings/{id}")
    fun putPost (
        @Path("id") id :Int,
        @Field("title") title : String
    ):Call<PostsForm>

    @DELETE("/api/v1/blog/postings/{id}")
    fun delPost (
        @Path("id") id : String
        ):Call<RegisterForm>

}