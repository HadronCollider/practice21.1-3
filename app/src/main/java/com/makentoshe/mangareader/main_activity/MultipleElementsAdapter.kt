package com.makentoshe.mangareader.main_activity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.makentoshe.mangareader.R
import com.makentoshe.mangareader.main_activity.networking.MainPageNetworkHandler
import com.makentoshe.mangareader.main_activity.networking.best_voted.BestVotedManga
import com.makentoshe.mangareader.main_activity.networking.daily_top.DailyTopManga
import com.makentoshe.mangareader.main_activity.networking.last_days_hot.LastDaysHotManga
import com.makentoshe.mangareader.main_activity.networking.new_chapters.NewChaptersManga
import okhttp3.OkHttpClient

class MultipleElementsAdapter(private val lifecycleScope: LifecycleCoroutineScope)
    : RecyclerView.Adapter<MultipleElementsAdapter.SuperBaseViewHolder>()  {

    private val client = OkHttpClient()
    private val networkHandler = MainPageNetworkHandler(client, lifecycleScope)

    abstract class SuperBaseViewHolder(itemView: View,
                                       protected val lifecycleScope: LifecycleCoroutineScope,
                                       protected val client: OkHttpClient) : RecyclerView.ViewHolder(itemView)

    abstract class BaseViewHolder<MangaType>(itemView: View,
                                  lifecycleScope: LifecycleCoroutineScope,
                                  client: OkHttpClient) : SuperBaseViewHolder(itemView, lifecycleScope, client)
        {
            abstract fun setContent(contentType: String, mangas: List<MangaType>)
        } // Abstract viewHolder

    // Its children
    class BestVotedViewHolder(itemView: View,
                              lifecycleScope: LifecycleCoroutineScope,
                              client: OkHttpClient)
        : BaseViewHolder<BestVotedManga>(itemView, lifecycleScope, client){

        private val recyclerView: RecyclerView = itemView.findViewById(R.id.bestVotedRecyclerView)

        override fun setContent(contentType: String, mangas: List<BestVotedManga>) {
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = BestVotedRecyclerViewAdapter(mangas, lifecycleScope, client)
        }
    }

    class DailyTopViewHolder(itemView: View,
                             lifecycleScope: LifecycleCoroutineScope,
                             client: OkHttpClient)
        : BaseViewHolder<DailyTopManga>(itemView, lifecycleScope, client){

        private val layout: LinearLayout = itemView.findViewById(R.id.popularToday)

        override fun setContent(contentType: String, mangas: List<DailyTopManga>) {
            layout.findViewById<TextView>(R.id.popularTodayTextView).text = contentType

            val recyclerView = layout.findViewById<RecyclerView>(R.id.dailyTopRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = DailyTopRecyclerViewAdapter(mangas, lifecycleScope, client)
        }
    }

    class LastDaysHotViewHolder(itemView: View,
                                lifecycleScope: LifecycleCoroutineScope,
                                client: OkHttpClient)
        : BaseViewHolder<LastDaysHotManga>(itemView, lifecycleScope, client){

        private val layout: LinearLayout = itemView.findViewById(R.id.hotNews)

        override fun setContent(contentType: String, mangas: List<LastDaysHotManga>) {
            layout.findViewById<TextView>(R.id.lastDaysHotTextView).text = contentType

            val recyclerView = layout.findViewById<RecyclerView>(R.id.lastDaysHotRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = LastDaysHotRecyclerViewAdapter(mangas, lifecycleScope, client)
        }
    }

    class NewChaptersViewHolder(itemView: View,
                                lifecycleScope: LifecycleCoroutineScope,
                                client: OkHttpClient)
        : BaseViewHolder<NewChaptersManga>(itemView, lifecycleScope, client){

        private val layout: LinearLayout = itemView.findViewById(R.id.newChapters)

        override fun setContent(contentType: String, mangas: List<NewChaptersManga>) { // Add logics for CheckBox
            layout.findViewById<TextView>(R.id.newChaptersTextView).text = contentType

            val recyclerView = layout.findViewById<RecyclerView>(R.id.newChaptersRecyclerView)
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = NewChaptersRecyclerViewAdapter(mangas, lifecycleScope, client)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return position // position specifies which ViewHolder wil be used
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperBaseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> BestVotedViewHolder(layoutInflater.inflate(R.layout.best_voted_recyclerview, parent,false), lifecycleScope, client)
            1 -> DailyTopViewHolder(layoutInflater.inflate(R.layout.daily_top, parent,false), lifecycleScope, client)
            2 -> LastDaysHotViewHolder(layoutInflater.inflate(R.layout.last_days_hot, parent, false), lifecycleScope, client)
            3 -> NewChaptersViewHolder(layoutInflater.inflate(R.layout.new_chapters, parent, false), lifecycleScope, client)
            else -> throw Exception("Wrong main page element!")
        }
    }

    override fun onBindViewHolder(holder: SuperBaseViewHolder, position: Int) {
            when(position){
                0 -> networkHandler.getBestVotedManga{ mangas -> (holder as BaseViewHolder<BestVotedManga>).setContent("Лучшие оценки", mangas)}
                1 -> networkHandler.getDailyTopManga{ mangas -> (holder as BaseViewHolder<DailyTopManga>).setContent("Популярно сегодня", mangas)}
                2 -> networkHandler.getLastDaysHotManga{ mangas -> (holder as BaseViewHolder<LastDaysHotManga>).setContent("Горячие новинки", mangas)}
                3 -> networkHandler.getNewChaptersManga{ mangas -> (holder as BaseViewHolder<NewChaptersManga>).setContent("Новые главы", mangas)}
            }
    }

    override fun getItemCount(): Int {
        return 4
    }

}