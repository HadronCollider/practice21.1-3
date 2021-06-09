package com.makentoshe.androidgithubcitemplate.manga_open.this_manga
import com.google.gson.annotations.SerializedName
import com.makentoshe.androidgithubcitemplate.manga_open.this_manga.AgeLimit
import com.makentoshe.androidgithubcitemplate.manga_open.this_manga.BookmarkType

data class Props(
    @SerializedName("admin_link")
    val adminLink: Any,
    @SerializedName("age_limit")
    val ageLimit: List<AgeLimit>,
    @SerializedName("bookmark_types")
    val bookmarkTypes: List<BookmarkType>,
    @SerializedName("can_pin_comment")
    val canPinComment: Boolean,
    @SerializedName("can_update")
    val canUpdate: Boolean,
    @SerializedName("can_upload_chapters")
    val canUploadChapters: Boolean,
    @SerializedName("can_watch_statistic")
    val canWatchStatistic: Boolean,
    @SerializedName("panel_link")
    val panelLink: Any,
    @SerializedName("promo_offer")
    val promoOffer: Any
)