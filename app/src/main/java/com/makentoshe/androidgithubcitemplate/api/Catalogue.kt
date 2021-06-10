package com.makentoshe.androidgithubcitemplate.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Catalogue {
    @GET("/api/search/catalog/?ordering=-rating&page=2&count=30") // /api/search/catalog/?ordering=-rating&page=2&count=30
    fun getCatalogue(
        @Query("page") // we may not have to write anything here if page' number is 1
        page: Int,
        @Query("ordering")
        ordering: String = "-rating",
        @Query("count")
        count: Int = 30
    ): Call<ResponseBody>
}