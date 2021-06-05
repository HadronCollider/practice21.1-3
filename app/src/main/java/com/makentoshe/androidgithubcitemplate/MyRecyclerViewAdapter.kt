package com.makentoshe.androidgithubcitemplate

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class MyRecyclerViewAdapter(
    private val data: List<Manga>, private val lifecycleCoroutineScope: LifecycleCoroutineScope
) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {

    private val client = OkHttpClient()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false).let(::MyViewHolder)

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.mangaTitle.text = data[position].title
        holder.mangaGenre.text = data[position].genre
        holder.mangaPreviewImage.setImageDrawable(null)

        lifecycleCoroutineScope.launch(Dispatchers.IO) {
            val response = client.newCall(Request.Builder().url(data[position].imageUrl).build()).execute()
            if (response.isSuccessful) {
                val bitmap = BitmapFactory.decodeStream(response.body?.byteStream())
                launch(Dispatchers.Main) {
                    holder.mangaPreviewImage.setImageBitmap(bitmap)
                }
            } else {
                println(response.message)
            }
        }
    }

    //class that handle single element of recycler view
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mangaPreviewImage: ImageView = itemView.findViewById(R.id.mangaPreviewImage)
        var mangaTitle: TextView = itemView.findViewById(R.id.mangaTitle)
        var mangaGenre: TextView = itemView.findViewById(R.id.mangaGenre)
    }

}