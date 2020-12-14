package com.victorgomes.teste.mercadapp.myapplication.view.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.victorgomes.teste.mercadapp.myapplication.R
import com.victorgomes.teste.mercadapp.myapplication.databinding.FragmentProductListBinding
import com.victorgomes.teste.mercadapp.myapplication.view.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProductListFragment : Fragment() {

    companion object {
        fun newInstance() = ProductListFragment()
    }

    private val viewModel: ProductListViewModel by activityViewModels()

    private lateinit var viewBinding: FragmentProductListBinding
    private lateinit var listAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentProductListBinding.inflate(inflater, container, false).apply {
            viewModel = this@ProductListFragment.viewModel
        }
        viewBinding.lifecycleOwner = this.viewLifecycleOwner
        setUpRecyclerView()
        setUpListeners()
        initObservers()
        return viewBinding.root
    }

    private fun setUpListeners() {
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setTitle(getString(R.string.lista_de_produtos))
    }

    private fun initObservers() {
        viewModel.productList.observe(viewLifecycleOwner, { content ->
            listAdapter.swapData(content)
        })

        viewModel.error.observe(viewLifecycleOwner, { error ->
            logDebug(TAG, "Error:${error}")
            context.showLongToastMessage("Ocorreu um erro ao listar os produtos")
        })
    }

    private fun setUpRecyclerView() {
        if (!::listAdapter.isInitialized) {
            listAdapter = ListAdapter(emptyList())
            listAdapter.subscribeToItemSelection { view, position, item ->
                viewModel.triggerProductSelected(position)
            }
        }
        if (viewBinding.rvList.adapter != listAdapter) {
            context?.let {
                val layoutManager = GridLayoutManager(
                    context,
                    it.calculateNoOfColumns()
                )
                viewBinding.rvList.layoutManager = layoutManager
                viewBinding.rvList.setHasFixedSize(true)
            }
            viewBinding.rvList.adapter = listAdapter
        }
    }

}