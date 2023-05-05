package com.alejandro.linksstore.modules.shoppingproducts.interfaces

import com.alejandro.linksstore.models.Products

interface InterfaceShoppingPresenter {
    fun showProducts(products: List<Products>)
    fun showError(message: String)

}