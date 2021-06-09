package com.makentoshe.androidgithubcitemplate.manga_open.comment_section
import com.google.gson.annotations.SerializedName

data class Props(
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_items")
    val totalItems: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)