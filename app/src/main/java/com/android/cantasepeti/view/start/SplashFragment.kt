package com.android.cantasepeti.view.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.android.cantasepeti.R
import com.android.cantasepeti.databinding.FragmentSplashBinding
import com.android.cantasepeti.view.MainActivity

class SplashFragment : Fragment() {

    private var binding : FragmentSplashBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).hideBottomNavigationBar()

        binding?.btnMove?.setOnClickListener {
            val action = SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment()
            it.findNavController().navigate(action)
        }

        return binding?.root
    }

}