package com.victorgomes.test.myapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.victorgomes.test.myapplication.R
import com.victorgomes.test.myapplication.data.repositoryimpl.ProductRepositoryImpl
import com.victorgomes.test.myapplication.data.service.api.AmaroApi
import com.victorgomes.test.myapplication.data.service.impl.ProductServiceImpl
import com.victorgomes.test.myapplication.domain.model.ProductDetailDomain
import com.victorgomes.test.myapplication.view.productdetail.ProductDetailViewModel
import com.victorgomes.test.myapplication.view.productdetail.ProductDetailViewModelFactory
import com.victorgomes.test.myapplication.view.productlist.ProductListViewModel
import com.victorgomes.test.myapplication.view.productlist.ProductListViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private val productListViewModelFactory: ProductListViewModelFactory =
        ProductListViewModelFactory(
            repository = ProductRepositoryImpl(
                service = ProductServiceImpl(
                    AmaroApi.endpoint()
                )
            )
        )
    private val productListViewModel: ProductListViewModel by viewModels(factoryProducer = {
        productListViewModelFactory
    })

    private val productDetailViewModelFactory: ProductDetailViewModelFactory =
        ProductDetailViewModelFactory(
            repository = ProductRepositoryImpl(
                service = ProductServiceImpl(
                    AmaroApi.endpoint()
                )
            )
        )
    private val productDetailViewModel: ProductDetailViewModel by viewModels(factoryProducer = {
        productDetailViewModelFactory
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initObservers()
        setUpNavController()
    }

    private fun initObservers() {
        productListViewModel.productSelected.observe(this, {
            it?.let {
                val item = productListViewModel.getProductByPosition(it)
                productDetailViewModel.changeProductDetail(
                    ProductDetailDomain(
                        name = item?.name,
                        image = item?.image,
                        regularPrice = item?.regularPrice,
                        actualPrice = item?.actualPrice,
                        discountPercentage = item?.discountPercentage,
                        installments = item?.installments,
                        sizes = item?.sizes,
                    )
                )
                navController.navigate(R.id.productDetailFragment)
            }
        })
    }

    private fun setUpNavController() {
        navController = this.findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        if (::navController.isInitialized) {
            return navController.navigateUp()
        }
        return super.onSupportNavigateUp()
    }
}