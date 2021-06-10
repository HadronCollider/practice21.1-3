package com.makentoshe.androidgithubcitemplate.chapters.list_of_chapters
import com.google.gson.annotations.SerializedName

data class Publisher(
    @SerializedName("name")
    val name: String
)