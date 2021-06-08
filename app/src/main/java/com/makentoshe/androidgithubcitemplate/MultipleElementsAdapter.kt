package com.makentoshe.androidgithubcitemplate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class MultipleElementsAdapter(private val data: List<Pair<String, List<BitmapMangaWrapper>>>)
    : RecyclerView.Adapter<MultipleElementsAdapter.BaseViewHolder>()  {

    abstract class BaseViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView){
            abstract fun setContent(contentType: String, mangas: List<BitmapMangaWrapper>)
        } // Abstract viewHolder

    // Its children
    class TopFeedViewHolder(itemView: View)
        : BaseViewHolder(itemView){

        val recyclerView: RecyclerView = itemView.findViewById(R.id.topFeedRecyclerView)

        override fun setContent(contentType: String, mangas: List<BitmapMangaWrapper>) {
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = TopFeedRecyclerViewAdapter(mangas)
        }
    }

    class PopularTodayViewHolder(itemView: View)
        : BaseViewHolder(itemView){

        val layout: LinearLayout = itemView.findViewById(R.id.popularToday)

        override fun setContent(contentType: String, mangas: List<BitmapMangaWrapper>) {
            layout.findViewById<TextView>(R.id.popularTodayTextView).text = contentType

            val recyclerView = layout.findViewById<RecyclerView>(R.id.popularTodayRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = PopularTodayRecyclerViewAdapter(mangas)
        }
    }

    class HotNewsViewHolder(itemView: View)
        : BaseViewHolder(itemView){

        val layout: LinearLayout = itemView.findViewById(R.id.hotNews)

        override fun setContent(contentType: String, mangas: List<BitmapMangaWrapper>) {
            layout.findViewById<TextView>(R.id.hotNewsTextView).text = contentType

            val recyclerView = layout.findViewById<RecyclerView>(R.id.hotNewsRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = HotNewsRecyclerViewAdapter(mangas)
        }
    }

    class NewChaptersViewHolder(itemView: View)
        : BaseViewHolder(itemView){

        val layout: LinearLayout = itemView.findViewById(R.id.newChapters)

        override fun setContent(contentType: String, mangas: List<BitmapMangaWrapper>) { // Add logics for CheckBox
            layout.findViewById<TextView>(R.id.newChaptersTextView).text = contentType

            val recyclerView = layout.findViewById<RecyclerView>(R.id.newChaptersRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = NewChaptersRecyclerViewAdapter(mangas)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return position // position specifies which ViewHolder wil be used
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> TopFeedViewHolder(
                layoutInflater.inflate(R.layout.top_feed_recyclerview, parent,false))
            1 -> PopularTodayViewHolder(
                layoutInflater.inflate(R.layout.popular_today, parent,false))
            2 -> HotNewsViewHolder(layoutInflater.inflate(R.layout.hot_news, parent, false))
            3 -> NewChaptersViewHolder(layoutInflater.inflate(R.layout.new_chapters, parent, false))
            else -> throw Exception("Wrong main page element!")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.setContent(data[position].first, data[position].second)
    }

    override fun getItemCount(): Int {
        return data.size
    }


}