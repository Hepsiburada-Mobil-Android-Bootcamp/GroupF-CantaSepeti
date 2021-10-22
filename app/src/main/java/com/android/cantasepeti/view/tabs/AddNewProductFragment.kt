package com.android.cantasepeti.view.tabs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.android.cantasepeti.R
import com.android.cantasepeti.adapter.ProductHelper
import com.android.cantasepeti.databinding.FragmentAddNewProductBinding
import com.android.cantasepeti.entity.Product
import com.google.firebase.firestore.FirebaseFirestore


class AddNewProductFragment : Fragment() {

    private var binding:FragmentAddNewProductBinding? = null

    private var firestore: FirebaseFirestore? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  DataBindingUtil.inflate(inflater,R.layout.fragment_add_new_product, container, false)


        binding?.buttonSave?.setOnClickListener {
            save()

        }


        firestore = FirebaseFirestore.getInstance()

        return binding?.root

    }
    private fun save() {
        Log.e("AddProductFragment", "valid form.... şu an kaydedilebilirrrrr")
        val product = Product(
            name = binding?.productName?.text.toString(),
            image = binding?.productImage?.text.toString())
        ProductHelper.list.add(product)


        firestore?.collection("product")?.add(product)?.addOnSuccessListener {
            Log.e("AddNewProduct", "Eklendi..")

        }?.addOnFailureListener{
            Toast.makeText(context, "Product Eklenemedi", Toast.LENGTH_LONG).show()

        }



    }

}