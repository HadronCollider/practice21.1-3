package com.makentoshe.androidgithubcitemplate.chapters.one_chapter
import com.google.gson.annotations.SerializedName

data class Page(
    @SerializedName("count_comments")
    val countComments: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("page")
    val page: Int,
    @SerializedName("width")
    val width: Int
)