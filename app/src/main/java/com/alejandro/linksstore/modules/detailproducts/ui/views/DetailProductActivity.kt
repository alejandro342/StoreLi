package com.alejandro.linksstore.modules.detailproducts.ui.views

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.alejandro.linksstore.R
import com.alejandro.linksstore.databinding.ActivityDetailProductBinding
import com.alejandro.linksstore.interfacesgeneral.InterfaceButton
import com.alejandro.linksstore.models.Products
import com.alejandro.linksstore.modules.detailproducts.presenter.DetailProductPresenter
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class DetailProductActivity : AppCompatActivity(), View.OnClickListener, InterfaceButton,
    DetailProductPresenter.CallbackControlProduct {

    private var mBinding: ActivityDetailProductBinding? = null
    var mToolbar: Toolbar? = null
    private var mProduct: Products? = null
    val gson = Gson()
    var mDetailPresenter: DetailProductPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityDetailProductBinding.inflate(layoutInflater)
        val mView: View = mBinding!!.root
        setContentView(mView)
        mToolbar = mView.findViewById(R.id.toolbar)
        myToolbar()

        mBinding!!.ImgGoToCart.setOnClickListener(this)
        mBinding!!.ImgRemoveCart.setOnClickListener(this)
        mBinding!!.textViewCounterProductCart.setOnClickListener(this)
        mBinding!!.ImgAddCart.setOnClickListener(this)
        mBinding!!.btnAddToCartShopping.setOnClickListener(this)

        mDetailPresenter = DetailProductPresenter(this, this)
        mProduct = gson.fromJson(intent.getStringExtra("product"), Products::class.java)

        mDetailPresenter?.getProductoSharedPref()
        mDetailPresenter?.setCounterProduct(this)
        setDetailProduct()
    }

    @SuppressLint("SetTextI18n")
    fun setDetailProduct() {
        Picasso.get().load(mProduct?.image).into(mBinding?.imageView)
        mBinding!!.titleTextView.text = mProduct?.title
        mBinding!!.descriptionTextView.text = mProduct?.description
        mBinding!!.priceTextView.text = "$ ${mProduct?.price}"
        mBinding!!.textViewCount.text = "${mProduct?.ratingP?.count}"
       // mBinding!!.textViewRating.text = "${mProduct?.ratingP?.rate}"
        mBinding!!.ratingBar.rating = (mProduct?.ratingP?.rate?.toFloat() ?: Float) as Float
    }

    fun myToolbar() {
        mToolbar?.title = "Detalle del Producto"
        mToolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(mToolbar)
        mToolbar?.titleMarginStart = 100
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onClick(myItem: View?) {
        when (myItem) {

            mBinding!!.ImgGoToCart -> mDetailPresenter?.goToShoppingCart()
            mBinding!!.ImgAddCart -> mDetailPresenter?.addItemCart()
            mBinding!!.ImgRemoveCart -> mDetailPresenter?.removeItemCart()
            mBinding!!.btnAddToCartShopping -> mDetailPresenter?.validateQuantityProduct()
        }
    }

    override fun showButton() {
        mBinding!!.btnAddToCartShopping.visibility = View.VISIBLE
    }

    override fun hideButton() {
        mBinding!!.btnAddToCartShopping.visibility = View.GONE
    }

    override fun counterProduct(mQuantity: Int) {
        mBinding!!.textViewCounterProductCart.text = "${mQuantity}"
    }

    override fun clearCounterProduct(mQuantity: Int) {
        mBinding!!.textViewCounterProductCart.text = "${mQuantity}"
    }

    @SuppressLint("SetTextI18n")
    override fun priceProduct(mPrice: Double) {
        mBinding!!.priceTextView.text = "$ ${mPrice}"
    }

    @SuppressLint("SetTextI18n")
    override fun clearPriceProduct(mPrice: Double) {
        mBinding!!.priceTextView.text = "$ ${mPrice}"
    }
}