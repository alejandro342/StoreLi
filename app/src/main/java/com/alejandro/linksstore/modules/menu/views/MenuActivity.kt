package com.alejandro.linksstore.modules.menu.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.alejandro.linksstore.R
import com.alejandro.linksstore.databinding.ActivityMenuBinding
import com.alejandro.linksstore.modules.menu.presenter.PresenterMenu

class MenuActivity : AppCompatActivity(), View.OnClickListener {

    var mBinding: ActivityMenuBinding? = null
    var mPresenterMenu: PresenterMenu? = null
    private var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMenuBinding.inflate(layoutInflater)
        val mView: View = mBinding!!.root
        setContentView(mView)
        mToolbar = mView.findViewById(R.id.toolbar)
        myToolbar()
        mPresenterMenu = PresenterMenu(this)
        mBinding!!.GoToProducts.setOnClickListener(this)
        mBinding!!.GoToCart.setOnClickListener(this)
    }

    private fun myToolbar() {
        mToolbar?.title = "Menu"
        mToolbar?.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        setSupportActionBar(mToolbar)
        mToolbar?.titleMarginStart = 450
    }

    override fun onClick(mItem: View?) {
        when (mItem) {
            mBinding!!.GoToProducts -> mPresenterMenu?.goToProducts()
            mBinding!!.GoToCart -> mPresenterMenu?.goToCart()
        }
    }
}