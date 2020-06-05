package com.jvmori.openweather.forecast.presentation.ui

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout.HORIZONTAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jvmori.openweather.forecast.data.ForecastUI

class ForecastAdapter(
    private var items: List<ForecastUI>
) : RecyclerView.Adapter<ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        return ForecastViewHolder.create(parent)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        return holder.bind(items[position])
    }

    fun submitItems(items: List<ForecastUI>) {
        this.items = items
        notifyDataSetChanged()
    }

    companion object {
        fun initLinearAdapter(
            recyclerView: RecyclerView,
            context: Context,
            items: List<ForecastUI>
        ): ForecastAdapter {
            return ForecastAdapter(items).apply {
                recyclerView.adapter = this
                recyclerView.layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
            }
        }
    }
}