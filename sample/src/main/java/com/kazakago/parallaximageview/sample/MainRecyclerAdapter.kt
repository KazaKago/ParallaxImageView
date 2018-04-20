package com.kazakago.parallaximageview.sample

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_cell.view.*

class MainRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return 20
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerAdapter.ViewHolder {
        return ViewHolder(context, parent)
    }

    override fun onBindViewHolder(holder: MainRecyclerAdapter.ViewHolder, position: Int) {
        holder.itemView.parallaxImageView.setImageResource(when (position % 5) {
            0 -> R.drawable.img_q1
            1 -> R.drawable.img_q2
            2 -> R.drawable.img_q3
            3 -> R.drawable.img_q4
            else -> R.drawable.img_q5
        })
    }

    inner class ViewHolder(context: Context, parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(R.layout.view_cell, parent, false))

}
