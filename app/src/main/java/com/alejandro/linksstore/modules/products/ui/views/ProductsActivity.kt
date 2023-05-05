package com.alejandro.linksstore.modules.products.ui.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.alejandro.linksstore.R
import com.alejandro.linksstore.databinding.ActivityProductsBinding
import com.alejandro.linksstore.extenciones.myToast
import com.alejandro.linksstore.interfacesgeneral.InterfaceProgressBar
import com.alejandro.linksstore.models.Products
import com.alejandro.linksstore.modules.products.interfaces.InterfaceProductsPresenter
import com.alejandro.linksstore.modules.products.presenter.ProductsPresenter
import com.alejandro.linksstore.modules.products.ui.adapter.AdapterProducts

class ProductsActivity : AppCompatActivity(), InterfaceProductsPresenter, InterfaceProgressBar {
    private var mBinding: ActivityProductsBinding? = null
    private lateinit var mAdapterProducts: AdapterProducts
    private lateinit var presenter: ProductsPresenter
    private var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityProductsBinding.inflate(layoutInflater)
        val mView: View = mBinding!!.root
        setContentView(mView)
        mToolbar = mView.findViewById(R.id.toolbar)
        myToolbar()
        getInfo()
    }

    private fun getInfo() {

        mAdapterProducts = AdapterProducts(this)
        mBinding!!.rcviewProducts.adapter = mAdapterProducts
        mBinding!!.rcviewProducts.layoutManager = GridLayoutManager(this, 2)
        presenter = ProductsPresenter(this,this)
        presenter.getProducts()
    }

    private fun myToolbar() {
        mToolbar?.title = "Productos"
        mToolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(mToolbar)
        mToolbar?.titleMarginStart = 240
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showProducts(products: List<Products>) {
        mAdapterProducts.submitList(products)
    }

    override fun showError(message: String) {

    }

    override fun showProgressBar() {
        mBinding!!.ProgressBarProducts.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        mBinding!!.ProgressBarProducts.visibility = View.GONE
    }

}