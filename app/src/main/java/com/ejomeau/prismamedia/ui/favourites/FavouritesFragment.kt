package com.ejomeau.prismamedia.ui.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ejomeau.prismamedia.MainActivity
import com.ejomeau.prismamedia.R
import com.ejomeau.prismamedia.model.Product
import com.ejomeau.prismamedia.ui.adapter.ProductAdapter
import com.ejomeau.prismamedia.ui.adapter.ProductAdapterInterface
import com.ejomeau.prismamedia.ui.base.BaseFragment
import com.ejomeau.prismamedia.ui.base.BaseViewState
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesFragment : BaseFragment<FavouritesViewModel>(), ProductAdapterInterface {

    override val viewModel: FavouritesViewModel by viewModel()

    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavourites()
        adapter = ProductAdapter(ArrayList(), this)
        list_adapter.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        list_adapter.adapter = adapter
    }

    override fun bind() {
        viewModel.apply {
            products.observe(this@FavouritesFragment, Observer {
                adapter.refreshView(it)
            })
            product.observe(this@FavouritesFragment, Observer {
                adapter.removeItem(Pair(it, true))
            })
            viewState.observe(this@FavouritesFragment, Observer {
                when (it) {
                    is BaseViewState.Error -> (this@FavouritesFragment.activity as MainActivity).hideLoader()
                    is BaseViewState.StartLoading -> (this@FavouritesFragment.activity as MainActivity).showLoader()
                    is BaseViewState.StopLoading -> (this@FavouritesFragment.activity as MainActivity).hideLoader()
                }
            })
        }
    }

    override fun onFavoriteChanged(state: Boolean, product: Product) {
        viewModel.onFavoriteChanged(state, product)
    }
}