package com.alejandro.linksstore.modules.detailproducts.presenter

import android.app.Activity
import android.content.Intent
import android.util.Log
import com.alejandro.linksstore.extenciones.myToast
import com.alejandro.linksstore.interfacesgeneral.InterfaceButton
import com.alejandro.linksstore.models.Products
import com.alejandro.linksstore.modules.shoppingproducts.ui.views.products.ProductsShoppingActivity
import com.alejandro.linksstore.utils.SharedPref
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DetailProductPresenter(private var mContext: Activity, mButton: InterfaceButton) {

    private var mButton: InterfaceButton? = null
    private var mProduct: Products? = null
    private var gson = Gson()
    private var TAG = "ProductPresenter"

    init {
        this.mButton = mButton
        mProduct = gson.fromJson(mContext.intent.getStringExtra("product"), Products::class.java)
    }

    //agregar producto a carrito
    private var mSharedPref = SharedPref(mContext)
    private var mSelectProduct = ArrayList<Products>()

    private var mCallbackCounter: CallbackControlProduct? = null

    //el control de cantidad de productos
    private var mCountProducts = 0

    //controlar el precio
    private var mPriceProducts = 0.0

    private var mPriceFormat=0.0
    fun goToShoppingCart() {
        val mIntent = Intent(mContext, ProductsShoppingActivity::class.java)
        mContext.startActivity(mIntent)
    }

    //verificar que el producto este o no en la lista
    private fun getIndexProduct(idProduct: String): Int {
        for ((pos, p) in mSelectProduct.withIndex()) {
            if (p.id == idProduct) {
                return pos
            }
        }
        return -1
    }

    fun setCounterProduct(mCallbackCounter: CallbackControlProduct) {
        this.mCallbackCounter = mCallbackCounter
    }

    fun addItemCart() { //aumentar la cantidad de productos a comprar
        mCountProducts++
        //calcular precio
        mPriceProducts = mProduct?.price!! * mCountProducts
        mProduct?.quantity = mCountProducts
        mButton?.showButton()
        Log.d(TAG, "Productos [${mProduct?.quantity}]")
        Log.d(TAG, "Price [${mPriceProducts}]")
        mPriceFormat = kotlin.math.round(mPriceProducts * 100)/100.0
        mCallbackCounter?.counterProduct(mProduct?.quantity!!)
        mCallbackCounter?.priceProduct(mPriceFormat)

    }

    fun removeItemCart() {//disminuir la cantidad de productos a comprar
        if (mCountProducts > 0) {
            mCountProducts--
            //calcular precio
            mPriceProducts = mProduct?.price!! * mCountProducts
            mProduct?.quantity = mCountProducts
            Log.d(TAG, "Productos [${mProduct?.quantity}]")
            Log.d(TAG, "Price [${mPriceProducts}]")
            mPriceFormat = kotlin.math.round(mPriceProducts * 100)/100.0
            mCallbackCounter?.counterProduct(mProduct?.quantity!!)
            mCallbackCounter?.priceProduct(mPriceFormat)
            if (mProduct?.quantity == 0) {
                mButton?.hideButton()
            }
        } else {
            mContext.myToast("Productos 0")
        }
    }

    fun validateQuantityProduct() {
        if (mCountProducts > 0) {
            addToBag()

        } else {
            mContext.myToast("debe agregar un producto al carrito")
        }
    }

    //agregar producto al carrito
    private fun addToBag() {
        val index = getIndexProduct(mProduct?.id!!)//si existe el producto en SharedPref
        if (index == -1) {//el producto no existe en shared pref
            if (mProduct?.quantity == null) {
                mProduct?.quantity = mCountProducts
            }
            mSelectProduct.add(mProduct!!)

        } else {
            mSelectProduct[index].quantity += mCountProducts

        }
        //para guardar los productos
        mSharedPref.saveDataProducts("product", mSelectProduct)
        mContext.myToast("Producto agregado al carrito")
        mProduct?.quantity = 0
        mCountProducts = 0
        mPriceProducts = 0.0
        mCallbackCounter?.clearCounterProduct(mProduct?.quantity!!)
        mCallbackCounter?.clearPriceProduct(mProduct?.price!!)
        mButton?.hideButton()
    }

    fun getProductoSharedPref() {
        //validar si hay un producto
        if (!mSharedPref.getInformation("product").isNullOrBlank()
        ) {//si existe una orden en SHARED PREF
            //transformar una lista tipo json a tipo product
            val type = object : TypeToken<ArrayList<Products>>() {}.type
            mSelectProduct = gson.fromJson(mSharedPref.getInformation("product"), type)
            //si ya existe en el carrito editamos la cantidad
            val index = getIndexProduct(mProduct?.id!!)
            if (index != -1) {
                mProduct?.quantity = mSelectProduct[index].quantity
                mPriceProducts = mProduct?.price!! * mProduct?.quantity!!
            }
            //listar los productos seleccionados
            for (p in mSelectProduct) {
                Log.d(TAG, "Shared pref: $p")
            }
        }
    }

    interface CallbackControlProduct {
        fun counterProduct(mQuantity: Int)
        fun clearCounterProduct(mQuantity: Int)
        fun priceProduct(mPrice: Double)
        fun clearPriceProduct(mPrice: Double)
    }
}