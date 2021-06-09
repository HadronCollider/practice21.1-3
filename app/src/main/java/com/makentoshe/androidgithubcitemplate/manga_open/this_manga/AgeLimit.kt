package com.makentoshe.androidgithubcitemplate.manga_open.this_manga
import com.google.gson.annotations.SerializedName

data class AgeLimit(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)