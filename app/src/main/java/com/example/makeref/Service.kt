package com.example.makeref


import retrofit2.Call
import retrofit2.http.*




data class RegisterForm(
    var id: String?=null,
    var username: String?=null,
    var password1: String?=null,
    var password2: String?=null,
    var email:String?=null,
    var user_photo:String?=null

)

data class LoginForm(
    var id: String?=null,
    var username : String?=null,
    var email : String? = null,
    var password : String? =null
)

data class UserForm(
    var id: String?=null,
    var username : String?=null,
    var email : String? = null,
    var password : String? =null
)



//https://frozen-cove-44670.herokuapp.com/api/v1/registration/
interface Service_test {



    @GET("/api/v1/users/{id}")
    fun getReg(
        @Path("username")username: String
       // @Path("pw")pw:String
    ):Call<RegisterForm>

    @GET("/api/v1/users/{username}")
    fun getUser(
        @Path("username")username: String
        // @Path("pw")pw:String
    ):Call<UserForm>

    @FormUrlEncoded
    @POST("/api/v1/registration/")
    fun postReg(
        @Field("username") username: String?,
        @Field("password1") password1: String?,
        @Field("password2") password2: String?
    ):Call<RegisterForm>

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

}