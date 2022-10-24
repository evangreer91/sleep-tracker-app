package com.example.android.trackmysleepquality.sleeptracker

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.database.SleepNight

//you need to tell recycler view what kind of view holder it will be working with
//textItemViewHolder is a basic holder with a text view
//recycler view works directly with view holders
class SleepNightAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
    // RecyclerView won't use data directly
    var data = listOf<SleepNight>()

    // first, recyclerView needs to know how many items we are displaying
    override fun getItemCount() = data.size

    // second, we tell view holder how to draw an item
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.sleepQuality.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        TODO("Not yet implemented")
    }
}