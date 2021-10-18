package com.android.cantasepeti.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.android.cantasepeti.R
import com.android.cantasepeti.databinding.FragmentDetailBinding
import com.android.cantasepeti.view.start.SplashFragmentDirections

class DetailFragment : Fragment() {

    private var binding : FragmentDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).hideBottomNavigationBar()

        binding?.btnMove?.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailFragmentToHomeFragment()
            it.findNavController().navigate(action)
        }

        return binding?.root
    }

}