package com.victorgomes.test.myapplication.data.service

import com.victorgomes.test.myapplication.domain.model.ProductItemListDomain

interface ProductService {
    suspend fun getProductList(): List<ProductItemListDomain>
}