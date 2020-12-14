package com.victorgomes.test.myapplication.view.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.victorgomes.test.myapplication.domain.model.ProductItemListDomain
import com.victorgomes.test.myapplication.domain.repository.ProductRepository
import com.victorgomes.test.myapplication.view.util.BaseViewModel
import com.victorgomes.test.myapplication.view.util.SingleLiveData
import com.victorgomes.test.myapplication.view.util.setValueSafe

class ProductListViewModel(private val productRepository: ProductRepository) : BaseViewModel() {

    private val _productList: MutableLiveData<List<ProductItemListDomain>> = MutableLiveData()
    val productList: LiveData<List<ProductItemListDomain>>
        get() = _productList

    private val _listIsEmpty: MutableLiveData<Boolean> = MediatorLiveData<Boolean>()
        .apply {
            addSource(_productList) {
                val result = it.isNullOrEmpty() && _loading.value == false
                value = result
            }
            addSource(_loading) {
                val result = _productList.value.isNullOrEmpty() && it == false
                value = result
            }
            value = false
        }

    val listIsEmpty: LiveData<Boolean>
        get() = _listIsEmpty

    init {
        _listIsEmpty.value = false
        loadProductList()
    }

    private val _productSelected: SingleLiveData<Int> = SingleLiveData()
    val productSelected: LiveData<Int>
        get() = _productSelected

    private fun loadProductList() {
        runLoadingBlock {
            _productList.setValueSafe(
                productRepository.getList()
            )
        }
    }

    fun triggerProductSelected(position: Int) {
        _productSelected.setValueSafe(position)
    }

    fun getProductByPosition(position: Int): ProductItemListDomain? {
        return _productList.value?.getOrNull(position)
    }
}