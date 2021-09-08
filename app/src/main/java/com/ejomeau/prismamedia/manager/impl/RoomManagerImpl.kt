package com.ejomeau.prismamedia.manager.impl

import android.content.Context
import com.ejomeau.prismamedia.manager.RoomManager
import com.ejomeau.prismamedia.model.Product
import com.ejomeau.prismamedia.room.ProductDatabase
import io.reactivex.Completable
import io.reactivex.Single

class RoomManagerImpl(val context: Context) : RoomManager {

    private var dataBaseInstance: ProductDatabase = ProductDatabase.getDatabaseInstance(context)


    override fun getProducts(): Single<List<Product>> =
        dataBaseInstance.productDao().getAll()


    override fun deleteProduct(product: Product): Completable =
        dataBaseInstance.productDao().delete(product)


    override fun insertProduct(product: Product): Completable =
        dataBaseInstance.productDao().insert(product)

}