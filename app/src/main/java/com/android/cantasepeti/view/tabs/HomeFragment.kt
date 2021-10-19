package com.android.cantasepeti.view.tabs

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import com.android.cantasepeti.R
import com.android.cantasepeti.databinding.FragmentHomeBinding
import com.android.cantasepeti.view.MainActivity

class HomeFragment : Fragment() {

    private var binding : FragmentHomeBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).showBottomNavigationBar()

        binding?.buttonNext?.setOnClickListener {
            binding?.imageViewCard?.setImageResource(R.drawable.image_two)
        }
         startSlider()


        binding?.btnMove?.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
            it.findNavController().navigate(action)
        }

        val reycler_view = binding?.listRecyclerView

        return binding?.root
    }
    private fun startSlider() {
        val loopImages = listOf(R.drawable.imagecard, R.drawable.image_two)
        Handler().apply {
            val runnable = object : Runnable {
                var index = 0
                var imageView = binding?.imageViewCard
                override fun run() {
                    imageView?.setImageResource(loopImages[index])
                    index++
                    if (index > loopImages.size - 1) {
                        index = 0
                    }
                    postDelayed(this, 1000)
                }
            }
            postDelayed(runnable, 1000)
        }
    }

}