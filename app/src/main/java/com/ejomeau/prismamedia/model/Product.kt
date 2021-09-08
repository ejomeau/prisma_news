package com.ejomeau.prismamedia.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ejomeau.prismamedia.model.dto.ProductDto
import com.ejomeau.prismamedia.room.Converters
import java.util.*

@Entity(tableName = "prisma_product_database")
@TypeConverters(Converters::class)
data class Product(
    @PrimaryKey val id : String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "datePublication") val datePublication: Date,
    @ColumnInfo(name = "image") val image: String
){

}

fun ProductDto.fromDto(): Product =
    Product(
        id = this.resource?.id ?: UUID.randomUUID().toString(),
        title = this.title ?: "default title",
        datePublication = this.published ?: Calendar.getInstance().time,
        image = this.medias?.images?.firstOrNull()?.let {
            it.original?.url
                ?: "https://homolog-paris.com/wp-content/uploads/2020/07/default.jpg"
        } ?: "https://homolog-paris.com/wp-content/uploads/2020/07/default.jpg"
    )