package com.makentoshe.androidgithubcitemplate.chapters.list_of_chapters
import com.google.gson.annotations.SerializedName

data class Props(
    @SerializedName("branch_id")
    val branchId: Int,
    @SerializedName("total_items")
    val totalItems: Int
)