package com.jvmori.openweather.forecast.presentation.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import com.jvmori.openweather.R
import kotlinx.android.synthetic.main.condition_item.view.*

class ConditionItemView(context: Context, attrs: AttributeSet) : MaterialCardView(context, attrs) {
    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.ConditionItemView, 0, 0).apply {
            try {
                val inflater = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
                inflater.inflate(R.layout.condition_item, this@ConditionItemView, true)

                txtValue.text = getString(R.styleable.ConditionItemView_valueTxt)
                desc.text = getString(R.styleable.ConditionItemView_descTxt)
                icon.setImageDrawable(getDrawable(R.styleable.ConditionItemView_icon))
            } finally {
                recycle()
            }
        }
    }
}