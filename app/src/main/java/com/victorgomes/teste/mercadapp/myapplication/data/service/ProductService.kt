package com.victorgomes.teste.mercadapp.myapplication.data.service

import com.victorgomes.teste.mercadapp.myapplication.domain.model.ProductItemListDomain

interface ProductService {
    suspend fun getProductList(): List<ProductItemListDomain>
}