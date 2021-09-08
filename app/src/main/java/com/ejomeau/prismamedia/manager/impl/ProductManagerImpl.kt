package com.ejomeau.prismamedia.manager.impl

import com.ejomeau.prismamedia.manager.ProductManager
import com.ejomeau.prismamedia.model.Product
import com.ejomeau.prismamedia.model.fromDto
import com.ejomeau.prismamedia.retrofit.RetrofitClient
import com.ejomeau.prismamedia.service.ProductService
import io.reactivex.Single
import org.koin.core.component.inject

class ProductManagerImpl : ProductManager {

    val retrofitClient: RetrofitClient by inject()

    val baseUrl = "https://gist.githubusercontent.com"

    override fun getProducts(): Single<List<Product>> =
        retrofitClient.rxMakeRequest(baseUrl, ProductService::class.java)
            .getProducts()
            .map {
                it.data.items.map {
                    it.fromDto()
                }
            }

}
