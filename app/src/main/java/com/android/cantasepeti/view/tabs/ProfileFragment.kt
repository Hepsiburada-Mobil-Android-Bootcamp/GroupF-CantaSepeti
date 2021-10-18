package com.android.cantasepeti.view.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.android.cantasepeti.databinding.FragmentProfileBinding
import com.android.cantasepeti.view.MainActivity

class ProfileFragment : Fragment() {

    private var binding : FragmentProfileBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).showBottomNavigationBar()

        binding?.btnMove?.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToLoginFragment()
            it.findNavController().navigate(action)
        }

        return binding?.root
    }

}