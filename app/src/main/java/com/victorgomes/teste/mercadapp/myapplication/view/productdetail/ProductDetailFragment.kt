package com.victorgomes.teste.mercadapp.myapplication.view.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.victorgomes.teste.mercadapp.myapplication.databinding.FragmentProductDetailBinding
import com.victorgomes.teste.mercadapp.myapplication.view.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProductDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ProductDetailFragment()
    }

    private val viewModel: ProductDetailViewModel by activityViewModels()
    private lateinit var viewBinding: FragmentProductDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentProductDetailBinding.inflate(inflater, container, false).apply {
            viewModel = this@ProductDetailFragment.viewModel
        }
        viewBinding.lifecycleOwner = this.viewLifecycleOwner
        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.error.observe(viewLifecycleOwner, { error ->
            logDebug(TAG, "Error:${error}")
            context.showLongToastMessage("Ocorreu um erro ao buscar os dados do herói")
        })
    }
}