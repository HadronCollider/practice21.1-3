package com.makentoshe.androidgithubcitemplate.main_activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.makentoshe.androidgithubcitemplate.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request


class HotNewsRecyclerViewAdapter(private val data: List<Manga>,
                                 private val lifecycleScope: LifecycleCoroutineScope,
                                 private val client: OkHttpClient) :
    RecyclerView.Adapter<HotNewsRecyclerViewAdapter.TopViewHolder>(){

    private val cache  = HashMap<String, Bitmap>()

    //class that handle single element of recycler view
    class TopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var mangaPreviewImage: ImageView = itemView.findViewById(R.id.mangaPreviewImage)
        var mangaTitle: TextView = itemView.findViewById(R.id.mangaTitle)
        var mangaGenre: TextView = itemView.findViewById(R.id.mangaGenre)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.mangaTitle.text = data[position].title
        holder.mangaGenre.text = data[position].genre
        getMangaImage(data[position].imageUrl){ bitmap ->
            holder.mangaPreviewImage.setImageBitmap(bitmap)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.hot_news_recyclerview_element,
                parent, false)

        return TopViewHolder(itemView)
    }

    private fun getMangaImage(imageUrl: String, after: (image: Bitmap) -> Unit) {
        if (cache.containsKey(imageUrl)) {
            return after.invoke(cache[imageUrl]!!)
        }

        lifecycleScope.launch(Dispatchers.IO) {
            val response =
                client.newCall(Request.Builder().url(imageUrl).build()).execute()
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


}