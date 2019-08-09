package com.example.WhateverMyAge.Main

import com.example.WhateverMyAge.Guide.Settings.toast
import android.app.Activity
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.WhateverMyAge.Love.Comment
import com.example.WhateverMyAge.Love.Comments
import com.example.WhateverMyAge.Love.LoveArticles
import com.example.WhateverMyAge.signedin
import com.example.WhateverMyAge.user_name
import kotlinx.android.synthetic.main.activity_comments.*
import kotlinx.android.synthetic.main.activity_love_write_article.*
import kotlinx.android.synthetic.main.activity_my_information.*
import kotlinx.android.synthetic.main.activity_my_information.username
import kotlinx.android.synthetic.main.activity_sign_up.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.Multipart
import java.io.File


class ConnectServer(var activity: Activity) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://frozen-cove-44670.herokuapp.com/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

        .build()

    var server = retrofit.create(Service::class.java)

    fun Login(ID: String, PW: String) {
        server.login(
            ID,
            "",
            PW
        ).enqueue(object : Callback<LoginForm> {
            override fun onFailure(call: Call<LoginForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<LoginForm>, response: Response<LoginForm>) {
                //code = response?.code()
                if (response.code() == 200) {
                    activity.toast("로그인에 성공했습니다.")
                    // test.text = response?.body().toString()
                    signedin = response.body()?.user?.id!!.toInt()
                    getID(signedin)
                    Log.i("id", "$signedin")
                    activity.finish()
                }
                else {
                    val raw = response.raw().toString()
                    Log.e("error", "$raw")
                    activity.toast("아이디 또는 비밀번호를 확인해 주세요")
                }
            }
        })
    }

    fun getLikedUsers(id : Int) {
        server.getLikedUsers(id).enqueue(object : Callback<LikedForm> {
            override fun onFailure(call: Call<LikedForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                //activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<LikedForm>, response: Response<LikedForm>) {
                //code = response?.code()
                if (response.code() == 200) {
                    //user_name = response.body()?.username!!.toString()

                    //activity.toast("로그인 성공")
                    // test.text = response?.body().toString()
                    //activity.username.text = "내 이름 : " + response.body()?.username!!.toString() + ", 회원번호 : $signedin"
                }
                val raw = response.raw()?.toString()
                Log.i("error", "$raw")
                Log.i("error", " " + response.body().toString())
            }
        })
    }

    fun putLikedUser(id : Int, user : IntArray) {

    }

    fun uploadPic(file : MultipartBody.Part, reply : String) {
        server.uploadPic(file, reply).enqueue(object : Callback<PicForm> {
            override fun onFailure(call: Call<PicForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                //activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<PicForm>, response: Response<PicForm>) {
                //code = response?.code()
                if (response.code() == 200) {
                    //user_name = response.body()?.username!!.toString()

                    //activity.toast("로그인 성공")
                    // test.text = response?.body().toString()
                    //activity.username.text = "내 이름 : " + response.body()?.username!!.toString() + ", 회원번호 : $signedin"
                }
                val raw = response.raw()?.toString()
                Log.i("error", "$raw")
                Log.i("error", " " + response.body().toString())
            }
        })
    }

    fun getPic() {
        server.getImage().enqueue(object : Callback<List<PicForm>> {
            override fun onFailure(call: Call<List<PicForm>>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                //activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<List<PicForm>>, response: Response<List<PicForm>>) {
                //code = response?.code()
                if (response.code() == 200) {
                    //user_name = response.body()?.username!!.toString()

                    //activity.toast("로그인 성공")
                    // test.text = response?.body().toString()
                    //activity.username.text = "내 이름 : " + response.body()?.username!!.toString() + ", 회원번호 : $signedin"
                }
                val raw = response.raw()?.toString()
                Log.i("error", "$raw")
                Log.i("error", " " + response.body().toString())
            }
        })
    }


    fun getID(id: Int) {
        server.getReg(
            id
        ).enqueue(object : Callback<RegisterForm> {
            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                //code = response?.code()
                if (response.code() == 200) {
                    user_name = response.body()?.username!!.toString()

                    //activity.toast("로그인 성공")
                    // test.text = response?.body().toString()
                    //activity.username.text = "내 이름 : " + response.body()?.username!!.toString() + ", 회원번호 : $signedin"
                }
                val body = response.body()?.toString()
                Log.i("error", "$body")
            }
        })
    }

    fun registration(ID: String, PW: String, PWC: String) {
        server.postReg(//텍스트를 가져와 보낸다
            ID,
            PW,
            PWC
        ).enqueue(object : Callback<RegisterForm> {

            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {

                Log.e("서버와 통신 성공!", "Success")
                var code = response?.code()
                //Log.e("multiReg",code.toString())//중복 테스트
                var raw = response.raw().toString()
                if (code == 201) {
                    activity.toast("반갑습니다. " + activity.regID.text.toString() + "님")
                    activity.finish()
                } else if (code == 400) {
                    activity.toast("이미 있는 아이디 입니다. 다시 입력해주세요")
                }

                Log.i("회원가입 오류", "($raw")
            }

            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }
        })
    }


    fun delReg(ID: String) {
        server.deleteReg(ID).enqueue(object : Callback<RegisterForm> {
            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                //code = response?.code()
                if (response.code() == 204) {
                    activity.toast("로그인에 성공했습니다.")
                    // test.text = response?.body().toString()
                    Log.i("회원탈퇴", "$signedin")
                    signedin = 0
                    activity.finish()
                } else {
                    activity.username.text = response?.raw().toString()
                }
            }
        })
    }

    fun logout() {
        server.logout().enqueue(object : Callback<RegisterForm> {
            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                //code = response?.code()
                if (response.code() == 200) {
                    activity.toast("안녕히 가세요.")
                    // test.text = response?.body().toString()
                    Log.i("로그아웃", "$signedin")
                    signedin = 0
                    activity.finish()
                }

                else {
                    activity.username.text = response?.raw().toString()
                }
            }
        })
    }

    fun writeArticle(title : String, content : String, lat : Double, lng : Double, file : File?) {
        var part : MultipartBody.Part?
        if (file != null) {
            val fileReqBody = RequestBody.create(MediaType.parse("image/*"), file)
            part = MultipartBody.Part.createFormData("photo", file.getName(), fileReqBody)
        }

        else
            part = null
        server.postBlog(signedin, user_name, title, part, content, lat, lng).enqueue(object : Callback<RegisterForm> {
            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                //code = response?.code()
                val raw = response.raw().toString()

                if (response.code() == 201) {
                    Log.i("ddd", "$raw")
                    Log.i("글쓰기 완료", "$user_name")
                }

                else {
                    Log.i("ddd", "$raw")
                    Log.i("글쓰기 실패", "$user_name")
                }
            }
        })
    }

    fun postQuestion(title : String, content : String, file : File?) {
        var part : MultipartBody.Part?
        if (file != null) {
            val fileReqBody = RequestBody.create(MediaType.parse("image/*"), file)
            part = MultipartBody.Part.createFormData("q_photo", file.getName(), fileReqBody)
        }

        else
            part = null
        server.postQuestion(signedin, user_name, title, part, content).enqueue(object : Callback<QuestionForm> {
            override fun onFailure(call: Call<QuestionForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<QuestionForm>, response: Response<QuestionForm>) {
                //code = response?.code()
                val raw = response.raw().toString()

                if (response.code() == 201) {
                    Log.i("ddd", "$raw")
                    Log.i("글쓰기 완료", "$user_name")
                }

                else {
                    Log.i("ddd", "$raw")
                    Log.i("글쓰기 실패", "$user_name")
                }
            }
        })
    }



    fun putPost( id : Int, title : String, content: String, file : File?) {
        var part : MultipartBody.Part?
        if (file != null) {
            val fileReqBody = RequestBody.create(MediaType.parse("image/*"), file)
            part = MultipartBody.Part.createFormData("photo", file.getName(), fileReqBody)
        }

        else
            part = null

        server.putPost(id, part, title, content).enqueue(object : Callback<PostsForm> {
            override fun onFailure(call: Call<PostsForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
            }

            override fun onResponse(call: Call<PostsForm>, response: Response<PostsForm>) {
                val raw = response.raw().toString()
                val body = response.body()!!.toString()
                if (response?.code().toString() == "200") {
                    // test.text = response?.body().toString()
                }

                else {

                }
                Log.i("dsdsd", "$raw")
                Log.i("body", "$body")
            }
        })
    }

    fun putQuestion(id : Int, title : String, content: String, file : File?) {
        var part : MultipartBody.Part?
        if (file != null) {
            val fileReqBody = RequestBody.create(MediaType.parse("image/*"), file)
            part = MultipartBody.Part.createFormData("photo", file.getName(), fileReqBody)
        }

        else
            part = null

        server.putQuestion(id, part, title, content).enqueue(object : Callback<QuestionForm> {
            override fun onFailure(call: Call<QuestionForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
            }

            override fun onResponse(call: Call<QuestionForm>, response: Response<QuestionForm>) {
                val raw = response.raw().toString()
                val body = response.body()!!.toString()
                if (response?.code().toString() == "200") {
                    // test.text = response?.body().toString()
                }

                else {

                }
                Log.i("dsdsd", "$raw")
                Log.i("body", "$body")
            }
        })
    }

    fun postProfilePic(id : Int, file : File) {
        val fileReqBody = RequestBody.create(MediaType.parse("image/*"), file)
        val part = MultipartBody.Part.createFormData("user_photo", file.getName(), fileReqBody)

        server.postProfilePic(id, part, user_name).enqueue(object : Callback<Profile> {
            override fun onFailure(call: Call<Profile>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
            }

            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                //code = response?.code()
                if (response.code() == 204) {
                    // test.text = response?.body().toString()
                } else {

                }

                Log.i("댓삭", " " + response.raw().toString())
                Log.i("설마?", " " + response.body().toString())
                Log.i("사진", " " + file.name)
            }
        })
    }

    fun delPost (id :String) {
        server.delPost(id).enqueue(object : Callback<RegisterForm> {
            override fun onFailure(call: Call<RegisterForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<RegisterForm>, response: Response<RegisterForm>) {
                //code = response?.code()
                if (response.code() == 204) {
                   // activity.toast("로그인에 성공했습니다.")
                    // test.text = response?.body().toString()
                  //  Log.i("회원탈퇴", "$signedin")
                    //activity.finish()
                } else {
                    //activity.username.text = response?.raw().toString()
                }
                val raw = response.raw().toString()
                Log.i("dsds", "$raw")
            }
        })
    }

    fun delQuestion(id : Int) {
        server.delQuestion(id).enqueue(object : Callback<QuestionForm> {
            override fun onFailure(call: Call<QuestionForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
                activity.toast("인터넷이 없습니다.")
            }

            override fun onResponse(call: Call<QuestionForm>, response: Response<QuestionForm>) {
                //code = response?.code()
                if (response.code() == 204) {
                    // activity.toast("로그인에 성공했습니다.")
                    // test.text = response?.body().toString()
                    //  Log.i("회원탈퇴", "$signedin")
                    //activity.finish()
                } else {
                    //activity.username.text = response?.raw().toString()
                }
                val raw = response.raw().toString()
                Log.i("dsds", "$raw")
            }
        })
    }



    fun getPost (id : Int, activity : Activity) {
        server.getPost(id).enqueue(object : Callback<PostsForm> {
            override fun onFailure(call: Call<PostsForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
            }

            override fun onResponse(call: Call<PostsForm>, response: Response<PostsForm>) {
                val raw = response.raw().toString()
                val body = response.body()!!
                if (response?.code().toString() == "200") {
                    // test.text = response?.body().toString()
                    activity.userpic.setImageResource(activity.getResources().getIdentifier("@drawable/story1", "id", activity.packageName))
                    activity.username.text = body.author_username
                    activity.lovecontents.text = body.content
//                    activity.like.text = body.like.toString()
                    activity.comments.text = body.cnt.toString()
                }

                else {

                }
                Log.i("dsdsd", "$raw")
                Log.i("body", "$body")
            }
        })
    }

    fun postComment(posting : Int, reply : String, cl : RecyclerView) {
        server.postComment(posting, reply, user_name, signedin).enqueue(object : Callback<PostsForm> {
            override fun onFailure(call: Call<PostsForm>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
            }

            override fun onResponse(call: Call<PostsForm>, response: Response<PostsForm>) {
                val raw = response.raw().toString()
              //  val body = response.body()!!
                if (response?.code().toString() == "201") {
                    // test.text = response?.body().toString()
                    //commentlist = arrayListOf()
                    Log.i("posting", "$posting")
                    //var commentlist: ArrayList<Comment> = arrayListOf()
                   // Comments().getComment(commentlist, posting)
                }

                else {

                }
                Log.i("dsdsd", "$raw")
                Log.i("dssdssss", " " +posting + " " + reply + " " + user_name + " " + signedin)
            //    Log.i("body", "$body")
            }
        })
    }

    fun getComment(id : Int) {
        server.getComment(id).enqueue(object : Callback<List<CommentsForm>> {
            override fun onFailure(call: Call<List<CommentsForm>>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
            }

            override fun onResponse(call: Call<List<CommentsForm>>, response: Response<List<CommentsForm>>) {
                val raw = response.raw().toString()
                 val body = response?.body()
                if (response?.code().toString() == "200") {
                    // test.text = response?.body().toString()
                }

                else {

                }
                Log.i("dsdsd", "$raw")
                Log.i("body", "$body")
            }
        })
    }

    fun deleteComment(id : Int) {
        server.deleteComment(id).enqueue(object : Callback<Body> {
            override fun onFailure(call: Call<Body>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
            }

            override fun onResponse(call: Call<Body>, response: Response<Body>) {
                //code = response?.code()
                if (response.code() == 204) {
                    // test.text = response?.body().toString()
                } else {

                }

                Log.i("댓삭", " " + response.raw().toString())

            }
        })
    }

    fun getProfilePic(id : Int) {
        server.getProfilePic(id).enqueue(object : Callback<Profile> {
            override fun onFailure(call: Call<Profile>, t: Throwable) {
                Log.e("서버와 통신에 실패했습니다.", "Error!")
            }

            override fun onResponse(call: Call<Profile>, response: Response<Profile>) {
                //code = response?.code()
                if (response.code() == 204) {
                    // test.text = response?.body().toString()
                } else {

                }

            }
        })
    }

}