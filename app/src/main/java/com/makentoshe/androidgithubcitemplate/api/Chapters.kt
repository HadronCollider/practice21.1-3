package com.makentoshe.androidgithubcitemplate.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Chapters {
    @GET("/api/titles/chapters/") // /api/titles/chapters/?branch_id=24665
    fun getChaptersList(
        @Query("branch_id")  // branch_id is manga's id
        branch_id: Int?            // it sends a list of chapters starting from the last one
    ): Call<ResponseBody>

    @GET("/api/titles/chapters/{chapter_id}/") // /api/titles/chapters/494643/
    fun getOneChapter(
        @Path("chapter_id") // first chapter's id can also be taken from manga's json
        chapter_id: String?
    ): Call<ResponseBody>

    @GET("/api/activity/comments/") // /api/activity/comments/?chapter_id=494643&chapter_page=-1&page=1&ordering=-id
    fun getChapterComments(
        @Query("chapter_id")
        chapter_id: String,
        @Query("chapter_page")
        chapter_page: Int = -1,
        @Query("page")
        page: Int = 1,
        @Query("ordering")
        ordering: String = "-id"
    ): Call<ResponseBody>
}