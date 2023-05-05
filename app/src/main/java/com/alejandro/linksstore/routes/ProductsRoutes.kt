package com.alejandro.linksstore.routes

import com.alejandro.linksstore.models.Products
import retrofit2.Call
import retrofit2.http.GET

interface ProductsRoutes {

    @GET("products")
    fun getProducts(): Call<ArrayList<Products>>
}