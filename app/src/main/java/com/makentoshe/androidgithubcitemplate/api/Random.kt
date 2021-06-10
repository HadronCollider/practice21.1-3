package com.makentoshe.androidgithubcitemplate.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Random {
    @GET("/api/search/catalog/") // ?ordering=-random&page=2&salt=0.9831226675674294&count=30
    fun getRandom(
        @Query("ordering")
        ordering: String = "-random",
        @Query("count")
        count: Int = 30,
        @Query("salt") // salt is a random number with 0.{16 characters} type, u can check in the first comment
        salt: Double,
        @Query("page")
        page: Int
    ): Call<ResponseBody>
}