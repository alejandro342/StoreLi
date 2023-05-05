package com.alejandro.linksstore.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson

class SharedPref(mContext: Context) {

    private var prefs: SharedPreferences? = null

    init {
        prefs = mContext.getSharedPreferences("com.alejandro.linksstore", Context.MODE_PRIVATE)
    }

    //guardar la información de los productos
    fun saveDataProducts(key: String, objeto: Any) {
        try {
            val gson = Gson()
            val json = gson.toJson(objeto)

            with(prefs?.edit()) {
                this?.putString(key, json)
                this?.commit()
            }
        } catch (e: Exception) {
            Log.d("ERROR", "Err: ${e.message}")
        }
    }

    //obtener la información de los productos
    fun getInformation(key: String): String? {
        val information = prefs?.getString(key, "")
        return information
    }

    //limpiar carrito de compras
    fun removeProducts(key: String){
        prefs?.edit()?.remove(key)?.apply()
    }

}