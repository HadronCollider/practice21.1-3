package com.makentoshe.androidgithubcitemplate.chapters.list_of_chapters
import com.google.gson.annotations.SerializedName

data class ListOfChapters(
    @SerializedName("content")
    val content: List<Content>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)