package com.makentoshe.mangareader.manga_main_page_activity.networking.similar_mangas


import com.google.gson.annotations.SerializedName

data class SimilarMangas(
    @SerializedName("content")
    val content: List<SimilarMangaContent>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("props")
    val props: Props
)