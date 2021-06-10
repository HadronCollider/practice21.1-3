package com.makentoshe.androidgithubcitemplate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class ChaptersAdapter(private val dataSet: ArrayList<Chapter>) :
    RecyclerView.Adapter<ChaptersAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val chapter_title: TextView = itemview.findViewById<TextView>(R.id.title)
        val chapter_subtitle: TextView = itemview.findViewById<TextView>(R.id.subtitle)
        val number_of_chapter: TextView = itemview.findViewById<TextView>(R.id.num)

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {

        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.row, viewGroup, false)

        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.chapter_title.text = dataSet[position].Title
        viewHolder.chapter_subtitle.text = dataSet[position].Subtitle
        viewHolder.number_of_chapter.text = dataSet[position].Num

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}



