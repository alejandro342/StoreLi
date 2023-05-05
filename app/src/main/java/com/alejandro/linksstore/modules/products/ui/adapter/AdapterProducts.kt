package com.alejandro.linksstore.modules.products.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alejandro.linksstore.R
import com.alejandro.linksstore.models.Products
import com.alejandro.linksstore.modules.detailproducts.ui.views.DetailProductActivity
import com.squareup.picasso.Picasso

class AdapterProducts(val mContext: Context) :
    ListAdapter<Products, AdapterProducts.ProductsViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductsViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val products = getItem(position)
        holder.textViewName.text = products.title
        holder.textViewPrice.text = "$ ${products.price}"

        Picasso.get()
            .load(products.image)
            .into(holder.imageView)
        holder.itemView.setOnClickListener { goToDetailProduct(products) }
    }

    //ir a la vista detalle de producto
    fun goToDetailProduct(products: Products) {
        val mIntent = Intent(mContext, DetailProductActivity::class.java)
        mIntent.putExtra("product", products.toJson())
        mContext.startActivity(mIntent)
    }

    class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)
    }
}
private object DiffUtilCallback:DiffUtil.ItemCallback<Products>(){
    override fun areItemsTheSame(oldItem: Products, newItem: Products): Boolean =
        oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Products, newItem: Products): Boolean =
        oldItem == newItem
}