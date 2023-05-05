package com.alejandro.linksstore.models

import com.alejandro.linksstore.models.products.Rating
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class Products(
    @SerializedName("id") val id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("price") var price: Double,
    @SerializedName("description") val description: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("quantity") var quantity: Int,
    @SerializedName("rating") var ratingP: Rating
) {
    fun toJson(): String {
        return Gson().toJson(this)
    }

    override fun toString(): String {
        return "Products(id=$id, title=$title, price=$price, description=$description, image=$image, quantity=$quantity, ratingP=$ratingP)"
    }


}