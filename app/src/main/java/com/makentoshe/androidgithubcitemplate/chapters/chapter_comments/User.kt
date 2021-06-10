package com.makentoshe.androidgithubcitemplate.chapters.chapter_comments
import com.google.gson.annotations.SerializedName
import com.makentoshe.androidgithubcitemplate.chapters.chapter_comments.Avatar

data class User(
    @SerializedName("avatar")
    val avatar: Avatar,
    @SerializedName("id")
    val id: Int,
    @SerializedName("tagline")
    val tagline: Any,
    @SerializedName("username")
    val username: String
)