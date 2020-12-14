package com.victorgomes.test.myapplication.domain.repository

import com.victorgomes.test.myapplication.domain.model.ProductItemListDomain

interface ProductRepository {
    suspend fun getList(): List<ProductItemListDomain>
}