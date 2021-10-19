package com.android.cantasepeti.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.cantasepeti.R
import com.android.cantasepeti.databinding.ItemCardDesignBinding
import com.android.cantasepeti.entity.Product

class ProductAdapter(private val listProduct:ArrayList<Product>) :RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    inner class ProductViewHolder(private val binding:ItemCardDesignBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(product: Product) {
            // olmadı viewbinding problem olabilir
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(

            ItemCardDesignBinding.inflate(LayoutInflater.from(parent.context))

        )}

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(listProduct[position])
    }

    override fun getItemCount() = listProduct.size
}