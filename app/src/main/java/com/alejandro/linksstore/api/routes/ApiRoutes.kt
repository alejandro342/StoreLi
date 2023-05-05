package com.alejandro.linksstore.api.routes

import com.alejandro.linksstore.api.retrofit.RetrofitClient
import com.alejandro.linksstore.routes.ProductsRoutes

class ApiRoutes {
    private val BASE_URL = "https://fakestoreapi.com/"
    private val retrofit = RetrofitClient()

    fun getProductsRoutes():ProductsRoutes{
        return retrofit.getClient(BASE_URL).create(ProductsRoutes::class.java)
    }
}