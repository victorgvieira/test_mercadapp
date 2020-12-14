package com.victorgomes.teste.mercadapp.myapplication.data.repositoryimpl

import com.victorgomes.teste.mercadapp.myapplication.data.service.ProductService
import com.victorgomes.teste.mercadapp.myapplication.domain.model.ProductItemListDomain
import com.victorgomes.teste.mercadapp.myapplication.domain.repository.ProductRepository
import com.victorgomes.teste.mercadapp.myapplication.view.util.runInDefault

class ProductRepositoryImpl(private val service: ProductService) : ProductRepository {
    override suspend fun getList(): List<ProductItemListDomain> {
        var result = emptyList<ProductItemListDomain>()
        runInDefault {
            result = service.getProductList()
        }
        return result
    }
}