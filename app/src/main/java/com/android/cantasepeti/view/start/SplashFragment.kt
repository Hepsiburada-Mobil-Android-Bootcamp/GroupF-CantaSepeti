package com.android.cantasepeti.view.start

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.android.cantasepeti.R
import com.android.cantasepeti.databinding.FragmentSplashBinding
import com.android.cantasepeti.view.MainActivity
import com.android.cantasepeti.view.start.onBoarding.OnBoardingFragmentDirections
import com.android.cantasepeti.view.start.onBoarding.OnBoardingPrefManager
import com.bumptech.glide.Glide

class SplashFragment : Fragment() {

    private var binding: FragmentSplashBinding? = null
    private var prefManager: OnBoardingPrefManager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).hideBottomNavigationBar()
        prefManager = OnBoardingPrefManager(binding?.root!!.context)

        binding?.splashTitle?.startAnimation(AnimationUtils.loadAnimation(context, R.anim.slide))
        binding?.splash?.let { Glide.with(this).asGif().load(R.raw.bag).into(it) }
        Handler(Looper.getMainLooper()).postDelayed({
            if (prefManager?.isFirstTimeLaunch == false) {
                val action = SplashFragmentDirections.actionSplashFragmentToLoginFragment()
                view?.findNavController()?.navigate(action)
            } else {
                val action = SplashFragmentDirections.actionSplashFragmentToOnBoardingFragment()
                binding?.root?.findNavController()?.navigate(action)
            }
        }, 4000)
        return binding?.root

    }


}
