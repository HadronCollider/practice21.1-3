package com.makentoshe.androidgithubcitemplate.data_classes.newchapters
import com.google.gson.annotations.SerializedName

data class new_chapters(
    @SerializedName("content")
    val content: List<Content>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)