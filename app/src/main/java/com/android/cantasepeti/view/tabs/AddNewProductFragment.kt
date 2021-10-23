package com.android.cantasepeti.view.tabs

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.android.cantasepeti.R
import com.android.cantasepeti.adapter.ProductHelper
import com.android.cantasepeti.databinding.FragmentAddNewProductBinding
import com.android.cantasepeti.entity.Product
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*


class AddNewProductFragment : Fragment() {

    private var binding:FragmentAddNewProductBinding? = null

    private var firestore: FirebaseFirestore? = null

    lateinit var ImageUrl:Uri


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
            image = binding?.productImage.toString())
        ProductHelper.list.add(product)


        firestore?.collection("product")?.add(product)?.addOnSuccessListener {
            Log.e("AddNewProduct", "Eklendi..")

        }?.addOnFailureListener{
            Toast.makeText(context, "Product Eklenemedi", Toast.LENGTH_LONG).show()

        }

        binding?.buttonSelect?.setOnClickListener {
            selectImage()
        }

        binding?.buttonUpload?.setOnClickListener {
            uploadImage()
        }

        binding?.productImage?.setOnClickListener {

        }





    }

    private fun uploadImage() {

        val progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Uploading File...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")

        storageReference.putFile(ImageUrl).
                addOnSuccessListener {
                    binding?.productImage?.setImageURI(null)
                    Toast.makeText(context,"Succesfuly upload",Toast.LENGTH_SHORT).show()
                    if (progressDialog.isShowing) progressDialog.dismiss()
                }.addOnFailureListener{
                    if (progressDialog.isShowing) progressDialog.dismiss()
                    Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
        }
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        Log.e("fragment","burada")
        startActivityForResult(intent,0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == 0 && requestCode == RESULT_OK && data != null){
            ImageUrl = data?.data!!
            binding?.productImage?.setImageURI(ImageUrl)
        }
    }


}