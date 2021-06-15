package com.makentoshe.mangareader.api


import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MangaMainPageApi {
    @GET("/api/titles/{manga_name}/")
    fun getMangaDescription(
        @Path("manga_name")
        manga_name: String?
    ): Call<ResponseBody>

    @GET("/api/titles/chapters/")
    fun getChapters(
        @Query("branch_id")
        branch_id: Int
    ): Call<ResponseBody>

    @GET ("/api/titles/{manga_name}/similar") // /api/titles/hes-got-three-tyrant-brothers/similar/
    fun getSimilarManga(
        @Path("manga_name")
        manga_name: String?
    ): Call<ResponseBody>

    @GET("/api/activity/comments/") // /api/activity/comments/?title_id=45666&page=1&ordering=-id
    fun getComments(
        @Query("title_id")
        title_id: Int,     //every manga has a special id number
        @Query("page")
        page: Int = 1,
        @Query("ordering")
        ordering: String = "-id"
    ): Call<ResponseBody>
}