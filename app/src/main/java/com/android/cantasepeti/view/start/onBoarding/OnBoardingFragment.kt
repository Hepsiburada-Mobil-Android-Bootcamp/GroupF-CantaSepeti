package com.android.cantasepeti.view.start.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.android.cantasepeti.databinding.FragmentOnBoardingBinding
import com.android.cantasepeti.view.MainActivity


class OnBoardingFragment : Fragment() {

    private var binding : FragmentOnBoardingBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).hideBottomNavigationBar()
        return binding?.root


    }

}