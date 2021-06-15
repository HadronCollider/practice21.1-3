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
import com.makentoshe.mangareader.main_activity.networking.daily_top.DailyTopManga
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request


class DailyTopRecyclerViewAdapter(private val data: List<DailyTopManga>,
                                  private val lifecycleScope: LifecycleCoroutineScope,
                                  private val client: OkHttpClient):
    RecyclerView.Adapter<DailyTopRecyclerViewAdapter.TopViewHolder>(){

    private val cache  = HashMap<String, Bitmap>()

    //class that handle single element of recycler view
    class TopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val view: View = itemView
        var mangaPreviewImage: ImageView = view.findViewById(R.id.mangaPreviewImage)
        var mangaTitle: TextView = view.findViewById(R.id.mangaTitle)
        var mangaTypeAndYear: TextView = view.findViewById(R.id.mangaTypeAndYear)

        fun setOnClickListener(manga: DailyTopManga){
            val mangaForIntent = MangaForIntent(manga.dir, manga.id, manga.countChapters)
            view.setOnClickListener(ToMangaPageOnClickListener(mangaForIntent))
        }
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.setOnClickListener(data[position])

        holder.mangaTitle.text = data[position].rusName
        holder.mangaTypeAndYear.text = "${data[position].type} ${data[position].issueYear}"
        getMangaImage(data[position].img.high){ bitmap ->
            holder.mangaPreviewImage.setImageBitmap(bitmap)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.daily_top_recyclerview_element,
                parent, false)

        return TopViewHolder(itemView)
    }

    private fun getMangaImage(imageUrl: String, after: (image: Bitmap) -> Unit) {
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