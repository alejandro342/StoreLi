package com.alejandro.linksstore.modules.shoppingproducts.presenter

import android.content.Context
import android.content.Intent
import android.util.Log
import com.alejandro.linksstore.extenciones.myToast
import com.alejandro.linksstore.interfacesgeneral.InterfaceButton
import com.alejandro.linksstore.interfacesgeneral.InterfaceCardview
import com.alejandro.linksstore.interfacesgeneral.InterfaceProgressBar
import com.alejandro.linksstore.models.Products
import com.alejandro.linksstore.modules.payments.view.PaymentsStatusActivity
import com.alejandro.linksstore.modules.shoppingproducts.ui.adapter.ShoppingAdapter
import com.alejandro.linksstore.utils.SharedPref
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShoppingPresenter(
    var mContext: Context, mCardView: InterfaceCardview,
    mProgressBar: InterfaceProgressBar,
    mBotton: InterfaceButton
) {

    var mSharedPref = SharedPref(mContext)
    var mGson = Gson()
    var mSelectProduct = ArrayList<Products>()

    //calcular el total a pagar
    var mTotal = 0.0

    var TAG = "ShoppingActivity"
    var mCallbackAdapter: CallbackAdapter? = null
    private var mAdapterShopping: ShoppingAdapter? = null
    private var mCardView: InterfaceCardview? = null
    private var mProgressBar: InterfaceProgressBar? = null
    private var mBotton: InterfaceButton? = null

    init {
        this.mProgressBar = mProgressBar
        this.mBotton = mBotton
        this.mCardView = mCardView
    }

    fun setShoppingAdapter(mCallbackAdapter: CallbackAdapter) {
        this.mCallbackAdapter = mCallbackAdapter
    }

    fun getProducts() {
        mProgressBar?.showProgressBar()
        //validar si hay productos
        if (!mSharedPref.getInformation("product").isNullOrBlank()) {
            //transformar una lista tipo json a tipo product
            val type = object : TypeToken<ArrayList<Products>>() {}.type
            mSelectProduct = mGson.fromJson(mSharedPref.getInformation("product"), type)
            mAdapterShopping = ShoppingAdapter(mSelectProduct)
            mCallbackAdapter?.getProductsP(mAdapterShopping!!)
            Log.d(TAG, "${mSelectProduct}")
            mProgressBar?.hideProgressBar()
            mBotton?.showButton()
            mCardView?.showCardView()
        } else {
            mContext.myToast("no tiene productos")
            Log.d(TAG, "${mSelectProduct}")
            mBotton?.hideButton()
            mProgressBar?.hideProgressBar()
            mCardView?.hideCardView()
        }
    }

    //obtener el total a pagar
    fun getTotalPayment() {
        for (p in mSelectProduct) {
            mTotal = mTotal + (p.quantity * p.price)
            mCallbackAdapter!!.getTotalP(mTotal)
        }
    }

    fun paymentRealized(){
        mSharedPref.removeProducts("product")
        goToFinalized()
    }
fun goToFinalized(){
    val mIntent= Intent(mContext,PaymentsStatusActivity::class.java)
    mIntent.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    mContext.startActivity(mIntent)
}
    interface CallbackAdapter {
        fun getProductsP(mAdapterShopping: ShoppingAdapter)
        fun getTotalP(mToltal: Double)
    }
}