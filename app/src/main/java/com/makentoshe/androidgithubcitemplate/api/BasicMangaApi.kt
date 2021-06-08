package com.makentoshe.androidgithubcitemplate.api

// implementation 'com.squareup.retrofit2:retrofit:2.9.0'
// we also have this retrofit implementation in build.gradle in \app

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BasicMangaApi {
    @GET("/api/titles/")
    fun getLastDaysManga(
        // /api/titles/?last_days=7&ordering=-votes
        @Query("last_days")
        lastDays: Int,
        @Query("ordering")
        ordering: String?
    ): Call<ResponseBody>
    fun getTheFirstPanelManga(
        // /api/titles/?ordering=-votes&count=30
        @Query("ordering")
        ordering: String?,
        @Query("count")
        count: Int?
    )

    @GET("/api/titles/{category}/")
    fun getNewChapters(
        // /api/titles/last-chapters/?page=1&count=40
        @Path("category")
        category: String,
        @Query("page")
        page: Int,
        @Query("count")
        count: Int?
    )
    fun getDailyTop(
        // /api/titles/daily-top/?count=7
        @Path("category")
        category: String,
        @Query("count")
        count: Int?

    )
}
