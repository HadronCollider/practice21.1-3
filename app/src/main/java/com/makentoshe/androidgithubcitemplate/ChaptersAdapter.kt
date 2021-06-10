package com.makentoshe.androidgithubcitemplate

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ChaptersAdapter(
    var ctx: Context,
    var ressourse: Int,
    var Items: ArrayList<Chapter>
) : ArrayAdapter<Chapter>(ctx, ressourse, Items) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(ctx)
        val view = layoutInflater.inflate(ressourse, null)

        val chapter_title: TextView = view.findViewById<TextView>(R.id.title)
        val chapter_subtitle: TextView = view.findViewById<TextView>(R.id.subtitle)
        val number_of_chapter: TextView = view.findViewById<TextView>(R.id.num)

        chapter_title.text = Items[position].Title
        chapter_subtitle.text = Items[position].Subtitle
        number_of_chapter.text = Items[position].Num

        // image.setImageDrawable(ctx.resources.getDrawable(Items[position].Img))

        return view
    }


}