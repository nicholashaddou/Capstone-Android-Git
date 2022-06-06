package com.c22ps175.playlab.api

import com.c22ps175.playlab.database.response.ResponseSementara
import retrofit2.Call
import retrofit2.http.*

interface PlaylabApi {


        //GET dan POST harus disesuaikan dengan yang kita butuhkan
            @FormUrlEncoded
            @POST("register")
            fun postRegister(
                @Field("name") name: String,
                @Field("email") email : String,
                @Field("password") password: String
            ): Call<ResponseSementara>
        //
        //    @FormUrlEncoded
        //    @POST("login")
        //    fun postLogin(
        //        @Field("email") email : String,
        //        @Field("password") password: String
        //    ): Call<LoginResponse>
}
