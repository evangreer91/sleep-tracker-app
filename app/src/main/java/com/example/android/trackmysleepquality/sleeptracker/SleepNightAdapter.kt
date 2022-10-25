package com.example.android.trackmysleepquality.sleeptracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.R
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.database.SleepNight

//you need to tell recycler view what kind of view holder it will be working with
//textItemViewHolder is a basic holder with a text view
//recycler view works directly with view holders
class SleepNightAdapter: RecyclerView.Adapter<TextItemViewHolder>() {
    // RecyclerView won't use data directly
    // add a custom setter to tell Kotlin when the data its displaying has changed
    // notifyDataSetChanged causes the recycler view to redraw everything on screen
    var data = listOf<SleepNight>()
        set (value) {
            field = value
            notifyDataSetChanged()
    }

    // first, recyclerView needs to know how many items we are displaying
    override fun getItemCount() = data.size

    // second, we tell view holder how to draw an item
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        if(item.sleepQuality <= 1) {
            holder.textView.setTextColor(Color.RED)
        } else {
            holder.textView.setTextColor(Color.BLACK)
        }

        holder.textView.text = item.sleepQuality.toString()
    }

    // when recycler view needs a new view holder, it creates one
    // parent is the view group into which the new view will be added after its bound to an adapter position
    // recycler view hands us the parent
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        //inflate text view from parent context
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.text_item_view, parent, false) as TextView

        return TextItemViewHolder(view)
    }
}