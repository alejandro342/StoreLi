package com.alejandro.linksstore.modules.menu.presenter

import android.content.Context
import android.content.Intent
import com.alejandro.linksstore.modules.products.ui.views.ProductsActivity
import com.alejandro.linksstore.modules.shoppingproducts.ui.views.products.ProductsShoppingActivity

class PresenterMenu(var mContext: Context) {
    fun goToProducts(){
        val mIntent= Intent(mContext,ProductsActivity::class.java)
        mContext.startActivity(mIntent)
    }


    fun goToCart(){
        val mIntent= Intent(mContext,ProductsShoppingActivity::class.java)
        mContext.startActivity(mIntent)
    }
}