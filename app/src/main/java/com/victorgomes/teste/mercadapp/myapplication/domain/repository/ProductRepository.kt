package com.victorgomes.teste.mercadapp.myapplication.domain.repository

import com.victorgomes.teste.mercadapp.myapplication.domain.model.ProductItemListDomain

interface ProductRepository {
    suspend fun getList(): List<ProductItemListDomain>
}