package com.makentoshe.androidgithubcitemplate.manga_open.comment_section
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar")
    val avatar: Avatar,
    @SerializedName("id")
    val id: Int,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("username")
    val username: String
)