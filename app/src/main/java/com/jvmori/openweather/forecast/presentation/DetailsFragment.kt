package com.jvmori.openweather.forecast.presentation


import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.jvmori.openweather.R
import com.jvmori.openweather.common.presentation.ui.BindingFragment
import com.jvmori.openweather.databinding.FragmentDetailsBinding

class DetailsFragment : BindingFragment(R.layout.fragment_details) {

    private lateinit var detailsBinding: FragmentDetailsBinding
    val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsBinding = (binding as FragmentDetailsBinding)
    }

    override fun onStart() {
        super.onStart()
        getArgs()
    }

    private fun getArgs() {
        try {
            detailsBinding.hello.text = args.currentWeather?.city
        } catch (e: IllegalStateException) {
            detailsBinding.hello.text = "Error"
        }
    }
}
