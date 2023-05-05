package com.alejandro.linksstore.modules.payments.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.alejandro.linksstore.R
import com.alejandro.linksstore.databinding.ActivityPaymentsStatusBinding
import com.alejandro.linksstore.modules.payments.presenter.PresenterPayment

class PaymentsStatusActivity : AppCompatActivity(), View.OnClickListener {
    var mBinding: ActivityPaymentsStatusBinding? = null
    var mPresenterPay: PresenterPayment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPaymentsStatusBinding.inflate(layoutInflater)
        val mView: View = mBinding!!.root
        setContentView(mView)
        mPresenterPay = PresenterPayment(this)
        mBinding!!.btnFinishBuy.setOnClickListener(this)
    }

    override fun onClick(mItem: View?) {
        when (mItem) {
            mBinding!!.btnFinishBuy -> mPresenterPay?.goToMenu()
        }
    }
}