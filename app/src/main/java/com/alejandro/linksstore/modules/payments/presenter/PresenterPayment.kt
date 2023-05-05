package com.alejandro.linksstore.modules.payments.presenter

import android.content.Context
import android.content.Intent
import com.alejandro.linksstore.modules.menu.views.MenuActivity

class PresenterPayment(var mContext: Context) {

    fun goToMenu() {
        val mIntent = Intent(mContext, MenuActivity::class.java)
        mIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        mContext.startActivity(mIntent)
    }
}