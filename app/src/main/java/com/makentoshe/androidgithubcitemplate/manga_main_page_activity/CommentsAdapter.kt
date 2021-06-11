package com.makentoshe.androidgithubcitemplate.manga_main_page_activity


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makentoshe.androidgithubcitemplate.R


class CommentsAdapter(private val comments: List<String>) :
    RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var userAvatar: ImageView = itemView.findViewById(R.id.userAvatar)
        var userName: TextView = itemView.findViewById(R.id.userName)
        var userComment: TextView = itemView.findViewById(R.id.userComment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.comments_fragment_element, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userComment.text = comments[position]
    }

    override fun getItemCount(): Int {
        return comments.size
    }

}