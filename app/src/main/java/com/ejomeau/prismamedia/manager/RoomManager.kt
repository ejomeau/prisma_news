package com.ejomeau.prismamedia.manager

import com.ejomeau.prismamedia.model.Product
import io.reactivex.Completable
import io.reactivex.Single
import org.koin.core.component.KoinComponent

interface RoomManager: KoinComponent {

    fun getProducts(): Single<List<Product>>

    fun deleteProduct(product: Product): Completable

    fun insertProduct(product: Product): Completable

}