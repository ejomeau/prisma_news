package com.ejomeau.prismamedia.model.dto

import java.util.*

data class ProductDto(
    val resource: Resource?,
    val published: Date?,
    val title: String?,
    val medias: Medias?
)

data class Resource(val id : String)
data class Medias(val images: List<Images?>)
data class Images(val  original: Original?)
data class Original(val url: String?)
