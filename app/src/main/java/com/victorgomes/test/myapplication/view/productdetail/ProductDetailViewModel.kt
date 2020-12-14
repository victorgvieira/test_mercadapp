package com.victorgomes.test.myapplication.view.productdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.victorgomes.test.myapplication.domain.model.ProductDetailDomain
import com.victorgomes.test.myapplication.domain.repository.ProductRepository
import com.victorgomes.test.myapplication.view.util.BaseViewModel
import com.victorgomes.test.myapplication.view.util.setValueSafe

class ProductDetailViewModel(private val productRepository: ProductRepository) : BaseViewModel() {

    private val _productDetail: MutableLiveData<ProductDetailDomain> = MutableLiveData()
    val productDetail: LiveData<ProductDetailDomain>
        get() = _productDetail

    fun changeProductDetail(productDetailDomain: ProductDetailDomain) {
        _productDetail.setValueSafe(productDetailDomain)
    }
}