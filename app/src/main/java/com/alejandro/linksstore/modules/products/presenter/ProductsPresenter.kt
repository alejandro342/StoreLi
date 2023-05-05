package com.alejandro.linksstore.modules.products.presenter


import com.alejandro.linksstore.interfacesgeneral.InterfaceProgressBar
import com.alejandro.linksstore.models.Products
import com.alejandro.linksstore.modules.products.interfaces.InterfaceProductsPresenter
import com.alejandro.linksstore.providers.ProductsProviders
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsPresenter(
    private val mView: InterfaceProductsPresenter,
    mProgressBar: InterfaceProgressBar
) {

    private var mProductsProviders: ProductsProviders? = null
    private var mProgressBar: InterfaceProgressBar? = null

    init {
        mProductsProviders = ProductsProviders()
        this.mProgressBar = mProgressBar
    }

    fun getProducts() {
        mProgressBar?.showProgressBar()
        mProductsProviders?.getProduct()?.enqueue(object : Callback<ArrayList<Products>> {
            override fun onResponse(
                call: Call<ArrayList<Products>>,
                response: Response<ArrayList<Products>>
            ) {
                if (response.body() != null) {
                    val productList = response.body()
                    if (productList != null) {
                        mView.showProducts(productList)
                        mProgressBar?.hideProgressBar()
                    } else {
                        mView.showError("no hay productos")
                        mProgressBar?.hideProgressBar()
                    }

                } else {
                    mView.showError("Error ${response.code()}: ${response.message()}")
                    mProgressBar?.hideProgressBar()
                }
            }

            override fun onFailure(call: Call<ArrayList<Products>>, t: Throwable) {
                mView.showError("Error: ${t.message}")
                mProgressBar?.hideProgressBar()
            }

        })
    }

}