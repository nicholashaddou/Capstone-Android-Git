package com.c22ps175.playlab.api

import com.c22ps175.playlab.database.response.*
import retrofit2.Call
import retrofit2.http.*

interface PlaylabApi {

    //endpoint ryan https://asia-southeast2-authentication-gamelab.cloudfunctions.net/user
    //referensi endpoint untuk user authentication https://firebase.google.com/docs/reference/rest/auth
    //endpoint user auth https://identitytoolkit.googleapis.com/v1/accounts:signInWithCustomToken?key=AIzaSyCC1-wFAEcY_AFH-s8LPuTCBtVC1TqFHmg
    // API KEY AIzaSyCC1-wFAEcY_AFH-s8LPuTCBtVC1TqFHmg

    @FormUrlEncoded
    @POST("verifyCustomToken")
    fun userAuth(
        @Field("token") token : String,
        @Field("returnSecureToken") returnSecureToken : Boolean
    ): Call<UserAuthResponse>

//    @FormUrlEncoded
//    @POST("signupNewUser")
//    fun postRegister(
//        @Field("email") email : String,
//        @Field("password") password: String,
//        @Field("returnSecureToken") returnSecureToken: Boolean
//    ): Call<RegisterResponseNew>
//
//    @FormUrlEncoded
//    @POST("verifyPassword")
//    fun postLogin(
//        @Field("email") email : String,
//        @Field("password") password : String,
//        @Field("returnSecureToken") returnSecureToken: Boolean
//    ): Call <LoginResponseNew>

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
