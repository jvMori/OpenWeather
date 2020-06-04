package com.jvmori.openweather.common.presentation.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

open class BaseAdapter<T>(
    private val layoutItem: Int,
    private val layoutExtraItem: Int
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onClickListener: IOnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        throw IllegalArgumentException("unknown view type $viewType")
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) {
            layoutExtraItem
        } else {
            layoutItem
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return this.itemCount + 1
    }

}