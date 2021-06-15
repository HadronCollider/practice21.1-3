package com.makentoshe.mangareader.main_activity

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
import com.makentoshe.mangareader.main_activity.networking.MainPageNetworkHandler
import com.makentoshe.mangareader.main_activity.networking.new_chapters.NewChaptersManga
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request


class NewChaptersRecyclerViewAdapter(private val data: List<NewChaptersManga>,
                                     private val lifecycleCoroutineScope: LifecycleCoroutineScope,
                                     private val client: OkHttpClient) :
    RecyclerView.Adapter<NewChaptersRecyclerViewAdapter.TopViewHolder>(){

    private val cache  = HashMap<String, Bitmap>()
    private val networkHandler = MainPageNetworkHandler(OkHttpClient(), lifecycleCoroutineScope) // TODO add pagination

    //class that handle single element of recycler view
    class TopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val view: View = itemView
        var mangaPreviewImage: ImageView = view.findViewById(R.id.mangaPreviewImage)
        var mangaTitle: TextView = view.findViewById(R.id.mangaTitle)
        var mangaChapter: TextView = view.findViewById(R.id.mangaChapter)
        var mangaPublishTime: TextView = view.findViewById(R.id.mangaChapterPublishTime)

        fun setOnClickListener(manga: NewChaptersManga){
            val mangaForIntent = MangaForIntent(manga.dir, manga.id, manga.countChapters)
            view.setOnClickListener(ToMangaPageOnClickListener(mangaForIntent))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.setOnClickListener(data[position])

        holder.mangaTitle.text = data[position].rusName
        holder.mangaChapter.text = data[position].chapter
        holder.mangaPublishTime.text = makeUploadDate(data[position].uploadDate) // Add special structure for last chapter e.t.c.

        getMangaImage(data[position].img.high){ bitmap ->
            holder.mangaPreviewImage.setImageBitmap(bitmap)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.new_chapters_recyclerview_element,
                parent, false)

        return TopViewHolder(itemView)
    }

    private fun getMangaImage(imageUrl: String, after: (image: Bitmap) -> Unit) {
        if (cache.containsKey(imageUrl)) {
            return after.invoke(cache[imageUrl]!!)
        }

        lifecycleCoroutineScope.launch(Dispatchers.IO) {
            val response =
                client.newCall(Request.Builder().url(makeCorrectImageUrl(imageUrl)).build()).execute()
            if (response.isSuccessful) {
                val bitmap = BitmapFactory.decodeStream(response.body?.byteStream())
                cache[imageUrl] = bitmap
                lifecycleCoroutineScope.launch(Dispatchers.Main){
                    after.invoke(bitmap)
                }
            } else {
                println(response.message)
            }
        }
    }

    private fun makeUploadDate(millis: Int): String{
        return "${millis / (1000 * 60)} minutes ago"
    }

    private fun makeCorrectImageUrl(imageUrl : String): String{
        return "https://api.remanga.org${imageUrl}"
    }

}