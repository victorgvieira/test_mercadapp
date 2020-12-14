package com.victorgomes.test.myapplication.data.service.model

import com.squareup.moshi.Json

class DataRaw<T>(
    @Json(name = "products")
    val data: T? = null,
)