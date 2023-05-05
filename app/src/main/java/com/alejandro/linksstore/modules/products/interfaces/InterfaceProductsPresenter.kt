package com.alejandro.linksstore.modules.products.interfaces

import com.alejandro.linksstore.models.Products

interface InterfaceProductsPresenter {

    fun showProducts(products: List<Products>)
    fun showError(message: String)
}