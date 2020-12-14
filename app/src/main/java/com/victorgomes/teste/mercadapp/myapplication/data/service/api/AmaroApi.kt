package com.victorgomes.teste.mercadapp.myapplication.data.service.api

import com.victorgomes.teste.mercadapp.myapplication.data.service.model.DataRaw
import com.victorgomes.teste.mercadapp.myapplication.data.service.model.ProductItemListRaw
import com.victorgomes.teste.mercadapp.myapplication.data.util.NetworkUtils
import retrofit2.http.GET

object AmaroApi {
    private const val BASE_URL = "http://www.mocky.io"

    fun endpoint(): Endpoint {
        val retrofit = NetworkUtils.createRetrofitBuilder(url = BASE_URL)
            .build()
        return retrofit.create(Endpoint::class.java)
    }

    interface Endpoint {
        @GET("v2/59b6a65a0f0000e90471257d")
        suspend fun getProductList(): DataRaw<List<ProductItemListRaw>>
    }
}
