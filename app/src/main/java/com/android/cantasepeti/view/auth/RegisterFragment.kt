package com.android.cantasepeti.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.android.cantasepeti.databinding.FragmentRegisterBinding
import com.android.cantasepeti.view.MainActivity

class RegisterFragment : Fragment() {

    private var binding : FragmentRegisterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).hideBottomNavigationBar()

        binding?.btnMove?.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToHomeFragment()
            it.findNavController().navigate(action)
        }

        return binding?.root
    }

}