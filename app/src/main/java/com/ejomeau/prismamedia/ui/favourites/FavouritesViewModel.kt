package com.ejomeau.prismamedia.ui.favourites

import androidx.lifecycle.MutableLiveData
import com.ejomeau.prismamedia.manager.RoomManager
import com.ejomeau.prismamedia.model.Product
import com.ejomeau.prismamedia.ui.base.BaseViewModel
import com.ejomeau.prismamedia.ui.base.BaseViewState
import com.ejomeau.prismamedia.utils.subscribeOnIo
import com.ejomeau.prismamedia.utils.subscribeOnMain
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import org.koin.core.component.inject

class FavouritesViewModel : BaseViewModel() {

    private val _products: MutableLiveData<MutableList<Pair<Product, Boolean>>> = MutableLiveData()
    val products: MutableLiveData<MutableList<Pair<Product, Boolean>>>
        get() = _products

    private val _product: MutableLiveData<Product> = MutableLiveData()
    val product: MutableLiveData<Product>
        get() = _product

    private val roomManager: RoomManager by inject()

    fun getFavourites() {
        roomManager.getProducts()
            .subscribeOnIo()
            .subscribeOnMain()
            .doOnSubscribe { postViewState(BaseViewState.StartLoading) }
            .subscribeBy(
                onError = {
                    it.message?.apply {
                        postViewState(BaseViewState.Error(this))
                    } ?: postViewState(BaseViewState.Error("Erreur"))
                },
                onSuccess = { favourites ->
                    postViewState(BaseViewState.StopLoading)
                    products.postValue(favourites.map { Pair(it, true) }.toMutableList())
                }
            )
            .addTo(compositeDisposable)
    }

    fun onFavoriteChanged(state: Boolean, product: Product) {
        roomManager.deleteProduct(product)
            .subscribeOnIo()
            .subscribeOnMain()
            .doOnSubscribe { postViewState(BaseViewState.StartLoading) }
            .subscribeBy(
                onError = {
                    it.message?.apply {
                        postViewState(BaseViewState.Error(this))
                    } ?: postViewState(BaseViewState.Error("Erreur"))
                },
                onComplete = {
                    postViewState(BaseViewState.StopLoading)
                    _product.postValue(product)
                }
            )
            .addTo(compositeDisposable)
    }
}