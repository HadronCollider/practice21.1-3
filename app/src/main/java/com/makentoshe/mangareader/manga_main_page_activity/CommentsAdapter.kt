package com.makentoshe.mangareader.manga_main_page_activity


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.makentoshe.mangareader.R
import com.makentoshe.mangareader.manga_main_page_activity.networking.comments.CommentsContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request


class CommentsAdapter(private val comments: List<CommentsContent>,
                      private val lifecycleScope: LifecycleCoroutineScope) :
    RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    private val cache  = HashMap<String, Bitmap>()
    private val client = OkHttpClient()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var userAvatar: ImageView = itemView.findViewById(R.id.userAvatar)
        var userName: TextView = itemView.findViewById(R.id.userName)
        var userComment: TextView = itemView.findViewById(R.id.userComment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.comments_fragment_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userComment.text = comments[position].text
        holder.userName.text = comments[position].user.username
        getImage(comments[position].user.avatar.high){bitmap ->
            holder.userAvatar.setImageBitmap(bitmap)
        }
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    private fun getImage(imageUrl: String, after: (image: Bitmap) -> Unit) {
        if (cache.containsKey(imageUrl)) {
            return after.invoke(cache[imageUrl]!!)
        }

        lifecycleScope.launch(Dispatchers.IO) {
            val response =
                client.newCall(Request.Builder().url(makeCorrectImageUrl(imageUrl)).build()).execute()
            if (response.isSuccessful) {
                val bitmap = BitmapFactory.decodeStream(response.body?.byteStream())
                cache[imageUrl] = bitmap
                lifecycleScope.launch(Dispatchers.Main){
                    after.invoke(bitmap)
                }
            } else {
                println(response.message)
            }
        }
    }

    private fun makeCorrectImageUrl(imageUrl : String): String{
        return "https://api.remanga.org${imageUrl}"
    }

}