package com.example.appbook2.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appbook2.R
import com.example.appbook2.data.model.ReadingProgress

class HistoryAdapter(private var historyList: List<ReadingProgress>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivCover: ImageView = itemView.findViewById(R.id.ivHistoryCover)
        val tvTitle: TextView = itemView.findViewById(R.id.tvHistoryTitle)
        val tvProgress: TextView = itemView.findViewById(R.id.tvHistoryProgress)
        val pbHistory: ProgressBar = itemView.findViewById(R.id.pbHistory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyList[position]
        holder.tvTitle.text = item.title
        
        val percentage = item.getProgressPercentage()
        holder.tvProgress.text = "Progres: $percentage%"
        holder.pbHistory.progress = percentage

        Glide.with(holder.itemView.context)
            .load(item.coverUrl)
            .placeholder(android.R.color.darker_gray)
            .into(holder.ivCover)
    }

    override fun getItemCount(): Int = historyList.size

    fun updateData(newData: List<ReadingProgress>) {
        historyList = newData
        notifyDataSetChanged()
    }
}
