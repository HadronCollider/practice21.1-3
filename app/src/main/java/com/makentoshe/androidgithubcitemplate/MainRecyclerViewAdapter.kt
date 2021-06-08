package com.makentoshe.androidgithubcitemplate
/*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainRecyclerViewAdapter(private val data: List<List<BitmapMangaWrapper>>)
    : RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder>() {

        private var recyclerId: Int = 0

        class MainViewHolder(itemView: View, recyclerId_: Int) : RecyclerView.ViewHolder(itemView){
            lateinit var recycler: View
            init{
                var recycler = when(recyclerId_){
                    0 -> itemView.findViewById<View>(R.id.popularToday)
                    else -> itemView.findViewById<View>(R.id.insertableRecyclerView)
                }
            }
        }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        if(position == 0){
            val recycler = holder.recycler.findViewById<RecyclerView>(R.id.popularTodayRecyclerView)
            val layoutManager = LinearLayoutManager(holder.recycler.context, LinearLayoutManager.HORIZONTAL, false)
            layoutManager.initialPrefetchItemCount = data[position].size
            recycler.layoutManager = layoutManager

            recycler.adapter = HorizontalRecyclerViewAdapter(data[position])

            holder.recycler = recycler
        } else {
            val layoutManager = LinearLayoutManager(holder.recycler.context, LinearLayoutManager.VERTICAL, false)
            layoutManager.initialPrefetchItemCount = data[position].size
            holder.recycler.layoutManager = layoutManager

            holder.recycler.adapter = PopularTodayRecyclerViewAdapter(data[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.top_feed_recyclerview,
                parent, false)

        return MainViewHolder(itemView, recyclerId++)
    }
}*/
