package com.makentoshe.androidgithubcitemplate.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WhatToReadRecommend {
    @GET("/api/titles/recommendations/") // /api/titles/recommendations/?r=1&count=20&page=1
    fun getRecommended(
        @Query("r")
        r: Int = 1,
        @Query("count = 20")
        count: Int = 20,
        @Query("page")
        page: Int = 1
    ): Call<ResponseBody>
}