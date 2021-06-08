package com.makentoshe.androidgithubcitemplate.data_classes.newchapter
import com.google.gson.annotations.SerializedName
import com.makentoshe.androidgithubcitemplate.data_classes.newchapter.BookmarkType

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