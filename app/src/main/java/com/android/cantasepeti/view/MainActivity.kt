package com.android.cantasepeti.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.android.cantasepeti.R
import com.android.cantasepeti.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var binding : ActivityMainBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //Bottom Nav Binding
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding?.bottomNavigationView?.setupWithNavController(navController)


    }

    fun hideBottomNavigationBar(){
        binding?.bottomNavigationView?.visibility = View.GONE
    }

    fun showBottomNavigationBar(){
        binding?.bottomNavigationView?.visibility = View.VISIBLE
    }
}