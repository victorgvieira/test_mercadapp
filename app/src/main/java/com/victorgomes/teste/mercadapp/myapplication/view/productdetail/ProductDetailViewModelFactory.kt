package com.victorgomes.teste.mercadapp.myapplication.view.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.victorgomes.teste.mercadapp.myapplication.domain.repository.ProductRepository

class ProductDetailViewModelFactory(private val repository: ProductRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java))
            return ProductDetailViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}