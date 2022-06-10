package com.c22ps175.playlab.api

import com.c22ps175.playlab.database.response.LoginResponse
import com.c22ps175.playlab.database.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.*

interface PlaylabApi {

     @FormUrlEncoded
     @POST("register")
     fun postRegister(
         @Field("name") name: String,
         @Field("email") email : String,
         @Field("password") password: String
     ): Call<RegisterResponse>

     @FormUrlEncoded
     @POST("login")
     fun postLogin(
         @Field("email") email : String,
         @Field("password") password: String
     ): Call<LoginResponse>
}
