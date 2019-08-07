package com.example.WhateverMyAge.Main

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.io.File
import java.net.URL

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
    var user_photo : String
)

data class PostsForm(
//    var id : String? = null,
//    var author_name : String? = null,
//      var opop : Array<Post?> = arrayOfNulls<Post?>(100)
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

data class CommentsForm (
    var posting : Int,
    var reply : String,
    var id: Int,
    var author_username: String,
    var author_id: Int
)

data class LikedForm (
    var likedusers : IntArray
)

//https://frozen-cove-44670.herokuapp.com/api/v1/registration/
interface Service {

    @GET("api/v1/blog/postings/{id}")
    fun getLikedUsers(
        @Path("id") id:Int
    ):Call<LikedForm>

    @FormUrlEncoded
    @PUT("api/v1/blog/postings/{id}")
    fun putLikedUser(
        @Path("id") id:Int,
        @Field("likedusers") likedusers : IntArray
    ):Call<LikedForm>

    //내 정보 받아오기
    @GET("/api/v1/users/{id}")
    fun getReg(                             
       @Path("id") id: Int
       // @Path("pw")pw:String
    ):Call<RegisterForm>

    @GET("api/v1/blog/postings/{id}")
    fun getPost(
        @Path("id") id : Int
    ): Call <PostsForm>

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
    @PUT("/api/v1/users/{id}/")
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

    @Multipart
    @POST("/api/v1/a")
    fun uploadPic (
        //@Path("id") id: String?,
       // @Field("user_photo") img : File
        @Part file : MultipartBody.Part,
       //@Part("picture") requestBody : RequestBody
        @Part("reply") reply : String
    ):Call<PicForm>

    @GET("/api/v1/a")
    fun getImage(
    ):Call<List<PicForm>>

    @Multipart
    @POST("/api/v1/blog/postings/")
    fun postBlog (
//        @Field("id") id : Int,
            @Part("author_id") author_id : Int,
          @Part("author_username") author_username : String?,
          @Part("title") title : String?,
//        @Part("like") like : Int,
            @Part file : MultipartBody.Part?,
            @Part("content") content : String?,
          //@Part("photo") photo : File? = null,
          @Part("lat") lat : Double?,
          @Part ("lng") lng : Double?
//        @Field("gps") gps : String?
    ):Call<RegisterForm>

    @GET("/api/v1/blog/postings/")
    fun showPost (
        //@Path("id") id : Int
    ):Call<List<PostsForm>>

    @Multipart
    @PUT("/api/v1/blog/postings/{id}")
    fun putPost (
        @Path("id") id :Int,
        //@Field("content") content : String,
        //@Field("title") title : String
        @Part file : MultipartBody.Part?,
        @Part("content") content : String?,
        @Part("title") title : String?
        ):Call<PostsForm>

    @Multipart
    @PUT("/api/v1/users/{id}")
    fun postProfilePic (
        @Path("id") id : Int,
        @Part file : MultipartBody.Part
    ):Call<Profile>

    @DELETE("/api/v1/blog/postings/{id}")
    fun delPost (
        @Path("id") id : String
        ):Call<RegisterForm>

    @FormUrlEncoded
    @POST("/api/v1/blog/comments/")
    fun postComment (
        @Field ("posting") posting : Int,
        @Field("reply") reply : String,
        @Field("author_username") author_username: String,
        @Field("author_id") author_id: Int
    ):Call<PostsForm>

    @GET("/api/v1/blog/postings/{id}/comments")
    fun getComment (
        @Path("id") id : Int
    ):Call <List<CommentsForm>>

    @DELETE("/api/v1/blog/comments/{id}/")
    fun deleteComment (
        @Path("id") id : Int
    ):Call<Body>
}