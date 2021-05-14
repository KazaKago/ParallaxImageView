package com.kazakago.parallaximageview.sample

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kazakago.parallaximageview.ParallaxImageView

class MainRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return 20
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerAdapter.ViewHolder {
        return ViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: MainRecyclerAdapter.ViewHolder, position: Int) {
        val parallaxImageView = holder.itemView.findViewById<ParallaxImageView>(R.id.parallaxImageView)
        parallaxImageView.setImageResource(when (position % 5) {
            0 -> R.drawable.img_q1
            1 -> R.drawable.img_q2
            2 -> R.drawable.img_q3
            3 -> R.drawable.img_q4
            else -> R.drawable.img_q5
        })
    }

    inner class ViewHolder(context: Context, parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_cell, parent, false))
}
