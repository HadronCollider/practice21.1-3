package com.makentoshe.mangareader.api

// implementation 'com.squareup.retrofit2:retrofit:2.9.0'
// we also have this retrofit implementation in build.gradle in \app

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface StartPageApi {
    @GET("/api/titles/")
    fun getLastDaysHotManga(
        // /api/titles/?last_days=7&ordering=-votes
        @Query("last_days")
        lastDays: Int = 7,
        @Query("ordering")
        ordering: String? = "-votes"
    ): Call<ResponseBody>

    @GET("/api/titles/")
    fun getBestVotedManga(
        // /api/titles/?ordering=-votes&count=30
        @Query("ordering")
        ordering: String? = "-votes",
        @Query("count")
        count: Int? = 30
    ): Call<ResponseBody>

    @GET("/api/titles/{category}/")
    fun getNewChapters(
        // /api/titles/last-chapters/?page=1&count=40
        @Path("category")
        category: String = "last-chapters",
        @Query("page")
        page: Int = 1,
        @Query("count")
        count: Int? = 40
    ): Call<ResponseBody>

    @GET("/api/titles/{category}/")
    fun getDailyTop(
        // /api/titles/daily-top/?count=7
        @Path("category")
        category: String = "daily-top",
        @Query("count")
        count: Int? = 7

    ): Call<ResponseBody>
}
