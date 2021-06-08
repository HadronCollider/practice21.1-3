
import com.google.gson.annotations.SerializedName
//
data class Content(
    @SerializedName("admin_rating")
    val adminRating: String,
    @SerializedName("avg_rating")
    val avgRating: String,
    @SerializedName("bookmark_type")
    val bookmarkType: Any,
    @SerializedName("count_chapters")
    val countChapters: Int,
    @SerializedName("cover_high")
    val coverHigh: String,
    @SerializedName("cover_low")
    val coverLow: String,
    @SerializedName("cover_mid")
    val coverMid: String,
    @SerializedName("dir")
    val dir: String,
    @SerializedName("en_name")
    val enName: String,
    @SerializedName("genres")
    val genres: List<Genre>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("img")
    val img: Img,
    @SerializedName("is_promo")
    val isPromo: Boolean,
    @SerializedName("issue_year")
    val issueYear: Int,
    @SerializedName("promo_link")
    val promoLink: String,
    @SerializedName("rus_name")
    val rusName: String,
    @SerializedName("total_views")
    val totalViews: Int,
    @SerializedName("total_votes")
    val totalVotes: Int,
    @SerializedName("type")
    val type: String
)