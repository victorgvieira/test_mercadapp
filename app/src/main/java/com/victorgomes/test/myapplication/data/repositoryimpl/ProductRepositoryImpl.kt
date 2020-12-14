package com.victorgomes.test.myapplication.data.repositoryimpl

import com.victorgomes.test.myapplication.data.service.ProductService
import com.victorgomes.test.myapplication.domain.model.ProductItemListDomain
import com.victorgomes.test.myapplication.domain.repository.ProductRepository
import com.victorgomes.test.myapplication.view.util.runInDefault

class ProductRepositoryImpl(private val service: ProductService) : ProductRepository {
    override suspend fun getList(): List<ProductItemListDomain> {
        var result = emptyList<ProductItemListDomain>()
        runInDefault {
            result = service.getProductList()
        }
        return result
    }
}