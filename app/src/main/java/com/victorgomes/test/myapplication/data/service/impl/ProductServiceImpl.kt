package com.victorgomes.test.myapplication.data.service.impl

import com.victorgomes.test.myapplication.data.service.ProductService
import com.victorgomes.test.myapplication.data.service.api.AmaroApi
import com.victorgomes.test.myapplication.data.util.NetworkErrorHandler
import com.victorgomes.test.myapplication.domain.model.ProductItemListDomain

class ProductServiceImpl constructor(private val api: AmaroApi.Endpoint) :
    ProductService {
    override suspend fun getProductList(): List<ProductItemListDomain> {
        return try {
            val result = api.getProductList()
            val list = result.data?.filter {
                it.name != null
            }?.map {
                ProductItemListDomain(
                    regularPrice = it.regularPrice,
                    actualPrice = it.actualPrice,
                    discountPercentage = it.discountPercentage,
                    installments = it.installments,
                    sizes = it.sizes
                        ?.filter { size -> size.available == true }
                        ?.map { size ->
                            size.size ?: "?"
                        }, name = it.name,
                    image = it.image
                )
            } ?: emptyList()
            list
        } catch (e: Throwable) {
            e.printStackTrace()
            throw NetworkErrorHandler.handlerNetworkError(error = e)
        }
    }
}