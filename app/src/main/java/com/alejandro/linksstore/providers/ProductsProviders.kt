package com.alejandro.linksstore.providers

import com.alejandro.linksstore.api.routes.ApiRoutes
import com.alejandro.linksstore.models.Products
import com.alejandro.linksstore.routes.ProductsRoutes
import retrofit2.Call

class ProductsProviders {
    private var mProductsRoutes:ProductsRoutes?=null
    init {
        val api=ApiRoutes()
        mProductsRoutes = api.getProductsRoutes()
    }
    fun getProduct():Call<ArrayList<Products>>?{
        return mProductsRoutes?.getProducts()
    }
}