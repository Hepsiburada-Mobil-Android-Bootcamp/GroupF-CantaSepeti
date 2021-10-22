package com.android.cantasepeti.view.auth

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import com.android.cantasepeti.R
import com.android.cantasepeti.databinding.FragmentRegisterBinding
import com.android.cantasepeti.entity.User
import com.android.cantasepeti.view.MainActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.regex.Pattern
import java.util.regex.Pattern.compile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class RegisterFragment : Fragment() {

    private var binding : FragmentRegisterBinding? = null

    private var firestore: FirebaseFirestore? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).hideBottomNavigationBar()

        firestore= FirebaseFirestore.getInstance()

        binding?.haveAccount?.setOnClickListener {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            it.findNavController().navigate(action)

        }
        binding?.btnRegister?.setOnClickListener {

            if(valid()){
                saveUser()
            }
            //val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            //it.findNavController().navigate(action)
        }



        return binding?.root
    }

    private fun valid(): Boolean {

        val userName= binding?.editUsername?.text
        val email= binding?.editEmail?.text
        val password = binding?.editPassword?.text

        val emailValidate = email?.trim()
        val userValidate = userName?.trim()
        val passwordValidate = password?.trim()

        val emailPattern: Pattern =compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+")

        val userPattern: Pattern= compile("^[a-zA-Z0-9[_]]+")


        val passwordPattern: Pattern = compile("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*?[#?!+@$%^&*-]).{3,}")




        if (userValidate.isNullOrEmpty()) {

            Toast.makeText(this.context,"Username is required.",Toast.LENGTH_SHORT).show()

            return false
        }
        else if (emailValidate.isNullOrEmpty()) {

            Toast.makeText(this.context,"E-mail is required.",Toast.LENGTH_SHORT).show()

            return false
        }
        else if (password.isNullOrEmpty()) {

            Toast.makeText(this.context,"Şifre boş olamaz.",Toast.LENGTH_SHORT).show()

            return false
        }else if (!emailPattern.matcher(emailValidate).matches() || emailValidate.length>50 || emailValidate.length<5){

            Toast.makeText(this.context,"E-mail is unvalid.",Toast.LENGTH_SHORT).show()

            return false

        }else if (!userPattern.matcher(userValidate).matches()){
            Toast.makeText(this.context,"Username can only include a-z, 0-9 and _ characters.",Toast.LENGTH_SHORT).show()

            return false
        }else if(userValidate.length<2){
            Toast.makeText(this.context,"Username is too short",Toast.LENGTH_SHORT).show()
            return false
        }else if(userValidate.length>40){
            Toast.makeText(this.context,"Username is too long",Toast.LENGTH_SHORT).show()
            return false
        }else if(!passwordPattern.matcher(passwordValidate).matches()){
            Toast.makeText(this.context,"Password must contain one digit, one uppercase letter, one lowercase letter and one special character.",Toast.LENGTH_SHORT).show()
            return false
        }else if(passwordValidate!!.length > 40){
            Toast.makeText(this.context,"Password is too long",Toast.LENGTH_SHORT).show()
            return false
        }else if(passwordValidate!!.length < 7){
            Toast.makeText(this.context,"Password is too short",Toast.LENGTH_SHORT).show()
            return false
        }



        else{
            return true
        }



    }

    private fun saveUser() {


        val user=User(
        userName = binding?.editUsername?.text.toString(),
        email = binding?.editEmail?.text.toString(),
        password =  binding?.editPassword?.text.toString()
        )

        val email:String=binding?.editEmail?.text.toString().trim { it<=' '}
        val password:String=binding?.editPassword?.text.toString().trim { it<=' '}

        firestore?.collection("users")?.add(user)?.addOnCompleteListener {task->
            when(task.isSuccessful){
                true->{
                    Toast.makeText(this.context,"Kullanıcı kaydedildi.",Toast.LENGTH_SHORT).show()

                }
                false->{
                    Toast.makeText(this.context,"Kaydedilemedi...",Toast.LENGTH_SHORT).show()
                }
            }
        }
            ?.addOnFailureListener {
                Log.e("HATA",it.message.toString())
                Toast.makeText(this.context,"Kaydedilemedi...",Toast.LENGTH_SHORT).show()
            }

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result!!.user!!
                    Toast.makeText(this.context,"Başarıyla hesap oluşturuldu.",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.loginFragment)
                } else {
                    Toast.makeText(
                        this.context,
                        task.exception!!.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


    }



}