package com.alejandro.linksstore.models.products

import com.google.gson.annotations.SerializedName

class Rating(
    @SerializedName("rate") var rate: Double,
    @SerializedName("count") var count: Int
) {
}