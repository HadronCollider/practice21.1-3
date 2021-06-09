package com.makentoshe.androidgithubcitemplate.main_activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.makentoshe.androidgithubcitemplate.R
import okhttp3.OkHttpClient

class MultipleElementsAdapter(private val data: List<Pair<String, List<Manga>>>,
                              private val lifecycleScope: LifecycleCoroutineScope)
    : RecyclerView.Adapter<MultipleElementsAdapter.BaseViewHolder>()  {

    private val client = OkHttpClient()

    abstract class BaseViewHolder(itemView: View,
                                  protected val lifecycleScope: LifecycleCoroutineScope,
                                  protected val client: OkHttpClient)
        : RecyclerView.ViewHolder(itemView){
            abstract fun setContent(contentType: String, mangas: List<Manga>)
        } // Abstract viewHolder

    // Its children
    class TopFeedViewHolder(itemView: View,
                            lifecycleScope: LifecycleCoroutineScope,
                            client: OkHttpClient)
        : BaseViewHolder(itemView, lifecycleScope, client){

        private val recyclerView: RecyclerView = itemView.findViewById(R.id.bestVotedRecyclerView)

        override fun setContent(contentType: String, mangas: List<Manga>) {
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = BestVotedRecyclerViewAdapter(mangas, lifecycleScope, client)
        }
    }

    class PopularTodayViewHolder(itemView: View,
                                 lifecycleScope: LifecycleCoroutineScope,
                                 client: OkHttpClient)
        : BaseViewHolder(itemView, lifecycleScope, client){

        private val layout: LinearLayout = itemView.findViewById(R.id.popularToday)

        override fun setContent(contentType: String, mangas: List<Manga>) {
            layout.findViewById<TextView>(R.id.popularTodayTextView).text = contentType

            val recyclerView = layout.findViewById<RecyclerView>(R.id.dailyTopRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = DailyTopRecyclerViewAdapter(mangas, lifecycleScope, client)
        }
    }

    class HotNewsViewHolder(itemView: View,
                            lifecycleScope: LifecycleCoroutineScope,
                            client: OkHttpClient)
        : BaseViewHolder(itemView, lifecycleScope, client){

        private val layout: LinearLayout = itemView.findViewById(R.id.hotNews)

        override fun setContent(contentType: String, mangas: List<Manga>) {
            layout.findViewById<TextView>(R.id.lastDaysHotTextView).text = contentType

            val recyclerView = layout.findViewById<RecyclerView>(R.id.lastDaysHotRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = LastDaysHotRecyclerViewAdapter(mangas, lifecycleScope, client)
        }
    }

    class NewChaptersViewHolder(itemView: View,
                                lifecycleScope: LifecycleCoroutineScope,
                                client: OkHttpClient)
        : BaseViewHolder(itemView, lifecycleScope, client){

        private val layout: LinearLayout = itemView.findViewById(R.id.newChapters)

        override fun setContent(contentType: String, mangas: List<Manga>) { // Add logics for CheckBox
            layout.findViewById<TextView>(R.id.newChaptersTextView).text = contentType

            val recyclerView = layout.findViewById<RecyclerView>(R.id.newChaptersRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = NewChaptersRecyclerViewAdapter(mangas, lifecycleScope, client)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return position // position specifies which ViewHolder wil be used
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> TopFeedViewHolder(
                layoutInflater.inflate(R.layout.best_voted_recyclerview, parent,false), lifecycleScope, client)
            1 -> PopularTodayViewHolder(
                layoutInflater.inflate(R.layout.daily_top, parent,false), lifecycleScope, client)
            2 -> HotNewsViewHolder(layoutInflater.inflate(R.layout.last_days_hot, parent, false), lifecycleScope, client)
            3 -> NewChaptersViewHolder(layoutInflater.inflate(R.layout.new_chapters, parent, false), lifecycleScope, client)
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