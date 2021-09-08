package com.ejomeau.prismamedia.service

import com.ejomeau.prismamedia.model.Data
import com.ejomeau.prismamedia.model.Envelop
import com.ejomeau.prismamedia.model.dto.ProductDto
import io.reactivex.Single
import retrofit2.http.GET

interface ProductService {

    @GET("julienbanse/34cdfbd1c094b2dddffce2b5d5533d6b/raw/15b5f322838e08bf8a38985b7aa94f6c758d6741/news.json")
    fun getProducts(
    ): Single<Envelop<ProductDto>>
}