package com.makentoshe.mangareader.manga_main_page_activity.networking.chapters


import com.google.gson.annotations.SerializedName

data class ChaptersContent(
    @SerializedName("chapter")
    val chapter: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("index")
    val index: Int,
    @SerializedName("is_bought")
    val isBought: Boolean,
    @SerializedName("is_paid")
    val isPaid: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String?,
    @SerializedName("pub_date")
    val pubDate: String?,
    @SerializedName("publishers")
    val publishers: List<Publisher>,
    @SerializedName("rated")
    val rated: Boolean,
    @SerializedName("score")
    val score: Int,
    @SerializedName("tome")
    val tome: Int,
    @SerializedName("upload_date")
    val uploadDate: String,
    @SerializedName("viewed")
    val viewed: Boolean,
    @SerializedName("volume_id")
    val volumeId: Any
)