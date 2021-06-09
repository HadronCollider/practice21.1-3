package com.makentoshe.androidgithubcitemplate.manga_open.this_manga
import com.google.gson.annotations.SerializedName

data class Publisher(
    @SerializedName("dir")
    val dir: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("type")
    val type: String
)