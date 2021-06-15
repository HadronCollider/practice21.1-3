package com.makentoshe.mangareader.manga_main_page_activity.networking.chapters


import com.google.gson.annotations.SerializedName

data class Props(
    @SerializedName("branch_id")
    val branchId: Int,
    @SerializedName("total_items")
    val totalItems: Int
)