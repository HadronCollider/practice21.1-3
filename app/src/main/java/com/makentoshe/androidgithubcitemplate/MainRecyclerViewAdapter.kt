package com.makentoshe.androidgithubcitemplate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainRecyclerViewAdapter(private val data: List<List<BitmapMangaWrapper>>)
    : RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder>() {

        private var recyclerId: Int = 0

        class MainViewHolder(itemView: View, recyclerId_: Int) : RecyclerView.ViewHolder(itemView){
            var recycler: RecyclerView = itemView.findViewById(R.id.insertableRecyclerView)
        }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val scrollType = when(position){
            0-> LinearLayoutManager.HORIZONTAL
            else -> LinearLayoutManager.VERTICAL
        }

        val layoutManager = LinearLayoutManager(holder.recycler.context, scrollType, false)
        layoutManager.initialPrefetchItemCount = data[position].size
        holder.recycler.layoutManager = layoutManager

        val adapter = when(position){
            0 -> HorizontalRecyclerViewAdapter(data[position])
            else -> VerticalRecyclerViewAdapter(data[position])
        }

        holder.recycler.adapter = adapter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.insertable_recyclerview,
                parent, false)

        return MainViewHolder(itemView, recyclerId++)
    }
}