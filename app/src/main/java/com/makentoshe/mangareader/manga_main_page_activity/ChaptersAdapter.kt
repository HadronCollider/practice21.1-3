package com.makentoshe.mangareader.manga_main_page_activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makentoshe.mangareader.R
import com.makentoshe.mangareader.manga_main_page_activity.networking.chapters.ChaptersContent

class ChaptersAdapter(private val data: List<ChaptersContent>/*,
                      private val lifecycleCoroutineScope: LifecycleCoroutineScope,
                      private val client: OkHttpClient*/) :
RecyclerView.Adapter<ChaptersAdapter.MyViewHolder>(){

    //class that handle single element of recycler view
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var mangaChapter: TextView = itemView.findViewById(R.id.mangaChapter)
        var publishTimeAndPublisher: TextView = itemView.findViewById(R.id.mangaPublishTimeAndPublisher)
        var likesCount: TextView = itemView.findViewById(R.id.likesCountTextView)
        //var likesButton: ImageButton = itemView.findViewById(R.id.likesButton)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        println(position)
        holder.mangaChapter.text = "Том ${data[position].tome}. Глава ${data[position].chapter}."
        holder.publishTimeAndPublisher.text = "${data[position].uploadDate} ${data[position].publishers[0].name}"
        holder.likesCount.text = data[position].score.toString() // Add special structure for last chapter e.t.c.
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.chapters_fragment_element,
                parent, false)

        return MyViewHolder(itemView)
    }
}