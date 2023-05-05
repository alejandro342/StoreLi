package com.alejandro.linksstore.modules.shoppingproducts.ui.views.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alejandro.linksstore.R
import com.alejandro.linksstore.databinding.ActivityProductsShoppingBinding
import com.alejandro.linksstore.interfacesgeneral.InterfaceButton
import com.alejandro.linksstore.interfacesgeneral.InterfaceCardview
import com.alejandro.linksstore.interfacesgeneral.InterfaceProgressBar
import com.alejandro.linksstore.models.Products
import com.alejandro.linksstore.modules.shoppingproducts.interfaces.InterfaceShoppingPresenter
import com.alejandro.linksstore.modules.shoppingproducts.presenter.ShoppingPresenter
import com.alejandro.linksstore.modules.shoppingproducts.ui.adapter.ShoppingAdapter
import com.alejandro.linksstore.utils.SharedPref


class ProductsShoppingActivity : AppCompatActivity(), InterfaceShoppingPresenter,
    ShoppingPresenter.CallbackAdapter, InterfaceProgressBar, InterfaceButton, InterfaceCardview,View.OnClickListener {

    private var mBinding: ActivityProductsShoppingBinding? = null
    var myTitle: RecyclerView? = null
    private lateinit var mAdapterShopping: ShoppingAdapter
    var mSharedPref: SharedPref? = null

    var mShoppingPresenter: ShoppingPresenter? = null
    private var mToolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityProductsShoppingBinding.inflate(layoutInflater)
        val mView: View = mBinding!!.root
        setContentView(mView)

        mToolbar = mView.findViewById(R.id.toolbar)
        myToolbar()
        mSharedPref = SharedPref(this)
        myTitle = findViewById(R.id.rcview_Products)
        mShoppingPresenter = ShoppingPresenter(this, this, this, this)

        mShoppingPresenter?.setShoppingAdapter(this)
        mBinding!!.btnAcceptShopping.setOnClickListener(this)
        mShoppingPresenter?.getProducts()
        mShoppingPresenter?.getTotalPayment()
        mBinding!!.rcvShoppingProducts.layoutManager = LinearLayoutManager(this)
    }

    private fun myToolbar() {
        mToolbar?.title = "Carrito de compras"
        mToolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(mToolbar)
        mToolbar?.titleMarginStart = 250
    }

    override fun showProducts(products: List<Products>) {
        mAdapterShopping.setData(products as ArrayList<Products>)
    }

    override fun showError(message: String) {

    }

    override fun getProductsP(mAdapterShopping: ShoppingAdapter) {
        mBinding!!.rcvShoppingProducts.adapter = mAdapterShopping
    }

    override fun getTotalP(mToltal: Double) {
        mBinding!!.mTotalPayment.text = "${mToltal}"
        Log.d("mTotal", "${mToltal}")
    }

    override fun showProgressBar() {
        mBinding!!.ProgressBarProductsShop.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        mBinding!!.ProgressBarProductsShop.visibility = View.GONE
    }

    override fun showButton() {
        mBinding!!.btnAcceptShopping.visibility = View.VISIBLE
    }

    override fun hideButton() {
        mBinding!!.btnAcceptShopping.visibility = View.GONE
    }

    override fun showCardView() {
        mBinding!!.CardViewTotal.visibility = View.VISIBLE
    }

    override fun hideCardView() {
        mBinding!!.CardViewTotal.visibility = View.GONE
    }

    override fun onClick(mItem: View?) {
        when(mItem){
            mBinding!!.btnAcceptShopping -> mShoppingPresenter?.paymentRealized()
        }
    }

}