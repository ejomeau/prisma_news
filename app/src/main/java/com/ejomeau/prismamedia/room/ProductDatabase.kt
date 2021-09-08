package com.ejomeau.prismamedia.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ejomeau.prismamedia.model.Product

@Database(entities = [Product::class], version = 3, exportSchema = false)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var databseInstance: ProductDatabase? = null

        fun getDatabaseInstance(mContext: Context): ProductDatabase =
            databseInstance ?: synchronized(this) {
                databseInstance ?: buildDatabaseInstance(mContext).also {
                    databseInstance = it
                }
            }

        private fun buildDatabaseInstance(mContext: Context) =
            Room.databaseBuilder(mContext, ProductDatabase::class.java, "product_table")
                .fallbackToDestructiveMigration()
                .build()

    }
}