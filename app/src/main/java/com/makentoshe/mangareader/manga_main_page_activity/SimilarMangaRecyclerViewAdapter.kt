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
import com.makentoshe.mangareader.manga_main_page_activity.networking.similar_mangas.SimilarMangaContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request


class SimilarMangaRecyclerViewAdapter(private val data: List<SimilarMangaContent>,
                                      private val lifecycleScope: LifecycleCoroutineScope,
                                      private val client: OkHttpClient) :
    RecyclerView.Adapter<SimilarMangaRecyclerViewAdapter.TopViewHolder>(){

    private val cache  = HashMap<String, Bitmap>()

    //class that handle single element of recycler view
    class TopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var mangaPreviewImage: ImageView = itemView.findViewById(R.id.mangaPreviewImage)
        var mangaTitle: TextView = itemView.findViewById(R.id.mangaTitle)
        var mangaType: TextView = itemView.findViewById(R.id.mangaType)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.mangaTitle.text = data[position].rusName
        holder.mangaType.text = data[position].type
        getImage(data[position].img.high){ bitmap ->
            holder.mangaPreviewImage.setImageBitmap(bitmap)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.similar_manga_recycler_element,
                parent, false)

        return TopViewHolder(itemView)
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