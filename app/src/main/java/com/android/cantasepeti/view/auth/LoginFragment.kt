package com.android.cantasepeti.view.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.android.cantasepeti.R
import com.android.cantasepeti.databinding.FragmentLoginBinding
import com.android.cantasepeti.view.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class LoginFragment : Fragment() {

    private var binding : FragmentLoginBinding? = null
    private var firestore: FirebaseFirestore? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).hideBottomNavigationBar()

        firestore= FirebaseFirestore.getInstance()


        binding?.createAccount?.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            it.findNavController().navigate(action)
        }

        binding?.btnLogin?.setOnClickListener {


            val email:String=binding?.loginUsername?.text.toString().trim { it<=' '}
            val password:String=binding?.loginPassword?.text.toString().trim { it<=' '}

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                       // val firebaseUser: FirebaseUser = task.result!!.user!!
                        findNavController().navigate(R.id.homeFragment)

                    } else {
                        Toast.makeText(
                            this.context, "Kullanıcı Adı veya Şifre yanlış",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


        }

        return binding?.root
    }

}