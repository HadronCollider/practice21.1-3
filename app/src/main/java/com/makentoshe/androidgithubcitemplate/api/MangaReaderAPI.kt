package com.makentoshe.androidgithubcitemplate.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MangaReaderAPI {
    @GET("/api/titles/{manga_name}/")
    fun getChosenManga(
        @Path("manga_name")
        manga_name: String?
    ): Call<ResponseBody>

    @GET ("/api/titles/{manga_name}/similar/") // /api/titles/hes-got-three-tyrant-brothers/similar/
    fun getSimilarManga(
        @Path("manga_name")
        manga_name: String?
        /*@Query("similar")
        similar: String?*/
    ): Call<ResponseBody>
}