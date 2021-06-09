package com.makentoshe.androidgithubcitemplate.manga_open.this_manga
import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("admin_rating")
    val adminRating: String,
    @SerializedName("age_limit")
    val ageLimit: Int,
    @SerializedName("another_name")
    val anotherName: String,
    @SerializedName("avg_rating")
    val avgRating: String,
    @SerializedName("bookmark_type")
    val bookmarkType: Any,
    @SerializedName("branches")
    val branches: List<Branche>,
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("continue_reading")
    val continueReading: Any,
    @SerializedName("count_bookmarks")
    val countBookmarks: Int,
    @SerializedName("count_chapters")
    val countChapters: Int,
    @SerializedName("count_rating")
    val countRating: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("dir")
    val dir: String,
    @SerializedName("en_name")
    val enName: String,
    @SerializedName("first_chapter")
    val firstChapter: FirstChapter,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: Img,
    @SerializedName("is_adsense_blocked")
    val isAdsenseBlocked: Int,
    @SerializedName("is_licensed")
    val isLicensed: Boolean,
    @SerializedName("issue_year")
    val issueYear: Int,
    @SerializedName("newlate_id")
    val newlateId: Any,
    @SerializedName("newlate_title")
    val newlateTitle: Any,
    @SerializedName("publishers")
    val publishers: List<PublisherX>,
    @SerializedName("related")
    val related: Any,
    @SerializedName("rus_name")
    val rusName: String,
    @SerializedName("status")
    val status: Status,
    @SerializedName("total_views")
    val totalViews: Int,
    @SerializedName("total_votes")
    val totalVotes: Int,
    @SerializedName("type")
    val type: Type
)