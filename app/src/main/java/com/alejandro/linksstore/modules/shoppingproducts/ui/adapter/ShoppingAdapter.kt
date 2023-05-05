package com.alejandro.linksstore.modules.shoppingproducts.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alejandro.linksstore.R
import com.alejandro.linksstore.models.Products
import com.squareup.picasso.Picasso

class ShoppingAdapter(private var mProducts: ArrayList<Products>) :
    RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoppingViewHolder {
        val mView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_shopping, parent, false)
        return ShoppingViewHolder(mView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val products = mProducts[position]
        Picasso.get()
            .load(products.image)
            .into(holder.imgImageProductItem)
        holder.textNameProductItem.text = products.title
        holder.textPriceProductItem.text = "$ ${products.price * products.quantity}"
        holder.textQuantityProducts.text = "${products.quantity}"
    }

    override fun getItemCount(): Int {
        return mProducts.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(products: ArrayList<Products>) {
        this.mProducts = products
        notifyDataSetChanged()
    }

    class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgImageProductItem: ImageView
        val textNameProductItem: TextView
        val textPriceProductItem: TextView
        val textQuantityProducts: TextView

        init {
            imgImageProductItem = itemView.findViewById(R.id.img_Item_Product_Shopping)
            textNameProductItem = itemView.findViewById(R.id.txt_Item_Name_Product_Shopping)
            textPriceProductItem = itemView.findViewById(R.id.txt_Item_Price_Product_Shopping)
            textQuantityProducts = itemView.findViewById(R.id.txt_Item_Quantity_Product_Shopping)
        }
    }
}