package com.ejomeau.prismamedia.room

import androidx.room.*
import com.ejomeau.prismamedia.model.Product
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ProductDao {
    @Query("SELECT * FROM prisma_product_database ORDER BY datePublication DESC")
    fun getAll(): Single<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg product: Product): Completable

    @Delete
    fun delete(product: Product): Completable
}