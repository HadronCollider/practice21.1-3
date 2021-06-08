package com.makentoshe.androidgithubcitemplate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NewChaptersRecyclerViewAdapter(private val data: List<BitmapMangaWrapper>) :
    RecyclerView.Adapter<NewChaptersRecyclerViewAdapter.TopViewHolder>(){

    //class that handle single element of recycler view
    class TopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var mangaPreviewImage: ImageView = itemView.findViewById(R.id.mangaPreviewImage)
        var mangaTitle: TextView = itemView.findViewById(R.id.mangaTitle)
        var mangaChapter: TextView = itemView.findViewById(R.id.mangaChapter)
        var mangaPublishTime: TextView = itemView.findViewById(R.id.mangaChapterPublishTime)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TopViewHolder, position: Int) {
        holder.mangaTitle.text = data[position].manga.title
        holder.mangaChapter.text = data[position].manga.genre
        holder.mangaPreviewImage.setImageBitmap(data[position].image)
        holder.mangaPublishTime.text = "Some minutes age" // Add special structure for last chapter e.t.c.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.new_chapters_recyclerview_element,
                parent, false)

        return TopViewHolder(itemView)
    }

}