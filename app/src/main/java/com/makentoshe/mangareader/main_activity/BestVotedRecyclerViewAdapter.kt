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
import com.makentoshe.mangareader.main_activity.networking.best_voted.BestVotedManga
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request


class BestVotedRecyclerViewAdapter(private val data: List<BestVotedManga>,
                                   private val lifecycleScope: LifecycleCoroutineScope,
                                   private val client: OkHttpClient
) :
RecyclerView.Adapter<BestVotedRecyclerViewAdapter.TopViewHolder>(){

    private val cache  = HashMap<String, Bitmap>()
    //class that handle single element of recycler view
    class TopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val view: View = itemView
        var mangaPreviewImage: ImageView = view.findViewById(R.id.mangaPreviewImage)
        var mangaTitle: TextView = view.findViewById(R.id.mangaTitle)
        var mangaGenre: TextView = view.findViewById(R.id.mangaGenre)

        fun setOnClickListener(manga: BestVotedManga){
            val mangaForIntent = MangaForIntent(manga.dir, manga.id, manga.countChapters)
            view.setOnClickListener(ToMangaPageOnClickListener(mangaForIntent))
        }
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.setOnClickListener(data[position])

        holder.mangaTitle.text = data[position].rusName
        holder.mangaGenre.text = data[position].genres[0].name
        getMangaImage(data[position].img.high){ bitmap ->
            holder.mangaPreviewImage.setImageBitmap(bitmap)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.best_voted_recyclerview_element,
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