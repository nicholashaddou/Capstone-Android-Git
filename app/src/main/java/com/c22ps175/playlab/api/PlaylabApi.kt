package com.c22ps175.playlab.ui.database

import com.c22ps175.playlab.ui.database.response.ResponseSementara
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaylabApi {


        //GET dan POST harus disesuaikan dengan yang kita butuhkan
        @GET("search/users")
        @Headers("Authorization: token ")
        fun getGithubUsers(@Query("q") query: String): Call<ResponseSementara>

        @GET("users/{username}")
        @Headers("Authorization: token ")
        fun getGithubFullname(
            @Path("username") username: String
        ): Call<ResponseSementara>

        @GET("users/{username}/following")
        @Headers("Authorization: token ")
        fun getGithubFollowerList(
            @Path("username") username: String
        ):Call<ArrayList<ResponseSementara>>

        @GET("users/{username}/followers")
        @Headers("Authorization: token $")
        fun getGithubFollowingList(
            @Path("username") username: String
        ):Call<ArrayList<ResponseSementara>>


}
