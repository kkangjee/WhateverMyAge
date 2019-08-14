package com.G1B4.WhateverMyAge.Main

import okhttp3.MultipartBody
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

data class PicForm (
    var picture : File? = null,
    var reply : String? = null
)

data class Profile (
    var user_photo : String?,
    var username : String?,
    var id : Int
)

data class PostsForm(
    var title : String,
    var author_username: String,
    var author_id : Int,
    var like : Int,
    var cnt: Int,
    var content : String,
    var lat : Double,
    var lng : Double,
    var id : Int,
    var photo : String?
)

data class LoginForm (
    var user : Body
)

data class Body (
    var username : String,
    var id : String
)

data class CommentsForm (
    var posting : Int,
    var reply : String,
    var id: Int,
    var author_username: String,
    var author_id: Int
)

data class QCommentsForm (
    var question : Int,
    var q_reply : String,
    var id: Int,
    var author_username: String,
    var author_id: Int
)

data class LikedForm (
    var likedusers : IntArray
)

data class QuestionForm (
    var id : Int,
    var q_title : String,
    var q_content : String,
    var q_photo : String?,
    var author_id: Int,
    var author_username: String,
    var cnt : Int
)

//https://frozen-cove-44670.herokuapp.com/api/v1/registration/
interface Service {
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

    //회원 탈퇴
    @DELETE("/api/v1/users/{id}")
    fun deleteReg(
        @Path("id")id: String?
    ):Call<RegisterForm>

    @Multipart
    @POST("/api/v1/blog/postings/")
    fun postBlog (
            @Part("author_id") author_id : Int,
          @Part("author_username") author_username : String?,
          @Part("title") title : String?,
            @Part file : MultipartBody.Part?,
            @Part("content") content : String?,
          @Part("lat") lat : Double?,
          @Part ("lng") lng : Double?
    ):Call<RegisterForm>

    @Multipart
    @PUT("/api/v1/users/{id}")
    fun postProfilePic (
        @Path("id") id : Int,
        @Part file : MultipartBody.Part?,
        @Part("username") username : String
    ):Call<Profile>

    @Multipart
    @POST("/api/v1/question/questions/")
    fun postQuestion (
        @Part("author_id") author_id : Int,
        @Part("author_username") author_username : String?,
        @Part("q_title") q_title : String?,
        @Part file : MultipartBody.Part?,
        @Part("q_content") content : String?
    ):Call<LoginForm>


    @GET("/api/v1/blog/postings/")
    fun showPost (
    ):Call<List<PostsForm>>

    @GET("/api/v1/question/questions/")
    fun showQuestions (
    ):Call<List<QuestionForm>>

    @GET("/api/v1/users/{id}")
    fun getProfilePic (
        @Path("id") id : Int
    ):Call<Profile>

    @Multipart
    @PUT("/api/v1/blog/postings/{id}")
    fun putPost (
        @Path("id") id :Int,
        @Part file : MultipartBody.Part?,
        @Part("content") content : String?,
        @Part("title") title : String?
        ):Call<PostsForm>

    @Multipart
    @PUT("/api/v1/question/questions/{id}/")
    fun putQuestion (
        @Path("id") id :Int,
        @Part file : MultipartBody.Part?,

        @Part("q_title") title : String?,
        @Part("q_content") content : String?
    ):Call<QuestionForm>

    @DELETE("/api/v1/blog/postings/{id}")
    fun delPost (
        @Path("id") id : String
        ):Call<RegisterForm>

    @DELETE("/api/v1/question/questions/{id}/")
    fun delQuestion (
        @Path("id") id : Int
    ):Call<QuestionForm>

    @FormUrlEncoded
    @POST("/api/v1/blog/comments/")
    fun postComment (
        @Field ("posting") posting : Int,
        @Field("reply") reply : String,
        @Field("author_username") author_username: String,
        @Field("author_id") author_id: Int
    ):Call<PostsForm>

    @FormUrlEncoded
    @POST("api/v1/question/q_comments/")
    fun postQComment(
        @Field ("question") question : Int,
        @Field("q_reply") q_reply : String,
        @Field("author_username") author_username: String,
        @Field("author_id") author_id: Int
    ):Call<QCommentsForm>

    @GET("/api/v1/blog/postings/{id}/comments")
    fun getComment (
        @Path("id") id : Int
    ):Call <List<CommentsForm>>

    @DELETE("/api/v1/blog/comments/{id}/")
    fun deleteComment (
        @Path("id") id : Int
    ):Call<Body>

    @GET("/api/v1/question/questions/{id}/q_comments")
    fun getQComment (
        @Path("id") id : Int
    ):Call <List<QCommentsForm>>

    @DELETE("/api/v1/question/q_comments/{id}/")
    fun deleteQComment (
        @Path("id") id : Int
    ):Call<Body>
}