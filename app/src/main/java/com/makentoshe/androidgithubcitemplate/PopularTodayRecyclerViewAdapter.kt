package com.makentoshe.androidgithubcitemplate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class PopularTodayRecyclerViewAdapter(private val data: List<BitmapMangaWrapper>) :
    RecyclerView.Adapter<PopularTodayRecyclerViewAdapter.TopViewHolder>(){

    //class that handle single element of recycler view
    class TopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var mangaPreviewImage: ImageView = itemView.findViewById(R.id.mangaPreviewImage)
        var mangaTitle: TextView = itemView.findViewById(R.id.mangaTitle)
        var mangaTypeAndYear: TextView = itemView.findViewById(R.id.mangaTypeAndYear)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.mangaTitle.text = data[position].manga.title
        holder.mangaTypeAndYear.text = data[position].manga.genre
        holder.mangaPreviewImage.setImageBitmap(data[position].image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.popular_today_recyclerview_element,
                parent, false)

        return TopViewHolder(itemView)
    }

}