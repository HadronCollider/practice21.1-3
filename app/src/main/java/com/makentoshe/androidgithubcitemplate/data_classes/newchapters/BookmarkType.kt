package com.makentoshe.androidgithubcitemplate.data_classes.newchapters
import com.google.gson.annotations.SerializedName

data class BookmarkType(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)