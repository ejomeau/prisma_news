package com.ejomeau.prismamedia.model

import java.util.*

data class Envelop<T>(val data: Data<T>)

data class Data<T>(
    val date: Date,
    val currentItemCount: Int,
    val items: List<T>
)
