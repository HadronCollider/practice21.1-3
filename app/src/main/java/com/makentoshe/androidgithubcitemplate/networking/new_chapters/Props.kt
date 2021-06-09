package com.makentoshe.androidgithubcitemplate.networking.new_chapters

import com.google.gson.annotations.SerializedName

data class Props(
    @SerializedName("bookmark_types")
    val bookmarkTypes: List<BookmarkType>,
    @SerializedName("page")
    val page: Int,
    @SerializedName("total_items")
    val totalItems: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)