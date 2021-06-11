package com.makentoshe.androidgithubcitemplate.manga_main_page_activity

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.makentoshe.androidgithubcitemplate.R
import okhttp3.OkHttpClient

class ChaptersAdapter(private val data: List<Chapter>,
    private val lifecycleCoroutineScope: LifecycleCoroutineScope,
    private val client: OkHttpClient) :
RecyclerView.Adapter<ChaptersAdapter.ViewHolder>(){

    private val cache  = HashMap<String, Bitmap>()

    //class that handle single element of recycler view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var mangaChapter: TextView = itemView.findViewById(R.id.mangaChapter)
        var publishTimeAndPublisher: TextView = itemView.findViewById(R.id.mangaPublishTimeAndPublisher)
        var likesCount: TextView = itemView.findViewById(R.id.likesCountTextView)
        var likesButton: ImageButton = itemView.findViewById(R.id.likesButton)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mangaChapter.text = data[position].chapterNumber
        holder.publishTimeAndPublisher.text = data[position].publishDateAndPublisher
        holder.likesCount.text = data[position].liesCount.toString() // Add special structure for last chapter e.t.c.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.chapters_fragment_element,
                parent, false)

        return ViewHolder(itemView)
    }
}