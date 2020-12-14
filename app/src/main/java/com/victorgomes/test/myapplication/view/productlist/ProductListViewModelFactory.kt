package com.victorgomes.test.myapplication.view.productlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.victorgomes.test.myapplication.domain.repository.ProductRepository

class ProductListViewModelFactory(private val repository: ProductRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductListViewModel::class.java))
            return ProductListViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}