package com.makentoshe.androidgithubcitemplate.chapters.one_chapter
import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("branch_id")
    val branchId: Int,
    @SerializedName("chapter")
    val chapter: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("index")
    val index: Int,
    @SerializedName("is_paid")
    val isPaid: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("pages")
    val pages: List<List<Page>>,
    @SerializedName("price")
    val price: Any,
    @SerializedName("pub_date")
    val pubDate: Any,
    @SerializedName("publishers")
    val publishers: List<Publisher>,
    @SerializedName("score")
    val score: Int,
    @SerializedName("title_id")
    val titleId: Int,
    @SerializedName("tome")
    val tome: Int,
    @SerializedName("upload_date")
    val uploadDate: String,
    @SerializedName("view_id")
    val viewId: Any,
    @SerializedName("volume_id")
    val volumeId: Any
)