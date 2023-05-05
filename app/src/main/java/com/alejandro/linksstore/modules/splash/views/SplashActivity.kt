package com.alejandro.linksstore.modules.splash.views

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.alejandro.linksstore.R
import com.alejandro.linksstore.databinding.ActivitySplashBinding
import com.alejandro.linksstore.modules.menu.views.MenuActivity

class SplashActivity : AppCompatActivity() {
    var mBinding: ActivitySplashBinding? = null

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySplashBinding.inflate(layoutInflater)
        val mView: View = mBinding!!.root
        validateSDK()
        setContentView(mView)
        animation()
    }

    @Suppress("DEPRECATION")
    fun validateSDK() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    fun animation() {
        val animation1: Animation = AnimationUtils.loadAnimation(this, R.anim.arriba)
        val animation2: Animation = AnimationUtils.loadAnimation(this, R.anim.abajo)
        mBinding!!.textSplas.animation = animation1
        mBinding!!.imageSplash.animation = animation2

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)
    }

}