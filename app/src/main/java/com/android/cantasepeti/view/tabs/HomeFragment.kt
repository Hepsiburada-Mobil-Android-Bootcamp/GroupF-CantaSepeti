package com.android.cantasepeti.view.tabs

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.cantasepeti.R
import com.android.cantasepeti.adapter.ProductAdapter
import com.android.cantasepeti.databinding.FragmentHomeBinding
import com.android.cantasepeti.entity.Product
import com.android.cantasepeti.view.MainActivity
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment() {


    var binding : FragmentHomeBinding? = null
    private var firestore: FirebaseFirestore? = null


    init {
        firestore = FirebaseFirestore.getInstance()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)

        binding?.imageViewAdd?.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddNewProductFragment()
            it.findNavController().navigate(action)
        }



//        binding?.floatingActionButton?.setOnClickListener {
//
//        }

        binding?.buttonNext?.setOnClickListener {
            binding?.imageViewCard?.setImageResource(R.drawable.image_two)
        }





        startSlider()
        bindProducts()



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


    private fun bindProducts(){
        firestore!!.collection("product").get().addOnSuccessListener{ snapshot ->
            snapshot.toObjects(Product::class.java)?.let { products ->
                binding?.listRecyclerView?.adapter = ProductAdapter(products as ArrayList<Product>)
            }


        }
    }






}