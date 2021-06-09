package com.makentoshe.androidgithubcitemplate.manga_open.similar_mangas
import com.google.gson.annotations.SerializedName
import com.makentoshe.androidgithubcitemplate.manga_open.similar_mangas.Content
import com.makentoshe.androidgithubcitemplate.manga_open.similar_mangas.Props

data class similarmanga(
    @SerializedName("content")
    val content: List<Content>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)