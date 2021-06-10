package com.makentoshe.androidgithubcitemplate.chapters.list_of_chapters
import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("chapter")
    val chapter: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("index")
    val index: Int,
    @SerializedName("is_bought")
    val isBought: Any,
    @SerializedName("is_paid")
    val isPaid: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("pub_date")
    val pubDate: String,
    @SerializedName("publishers")
    val publishers: List<Publisher>,
    @SerializedName("rated")
    val rated: Any,
    @SerializedName("score")
    val score: Int,
    @SerializedName("tome")
    val tome: Int,
    @SerializedName("upload_date")
    val uploadDate: String,
    @SerializedName("viewed")
    val viewed: Any,
    @SerializedName("volume_id")
    val volumeId: Any
)