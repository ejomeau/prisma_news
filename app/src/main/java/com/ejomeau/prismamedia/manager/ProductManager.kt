package com.ejomeau.prismamedia.manager

import com.ejomeau.prismamedia.model.Product
import io.reactivex.Single
import org.koin.core.component.KoinComponent

interface ProductManager: KoinComponent {

    fun getProducts() : Single<List<Product>>

}