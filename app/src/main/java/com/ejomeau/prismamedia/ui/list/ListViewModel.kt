package com.ejomeau.prismamedia.ui.list

import androidx.lifecycle.MutableLiveData
import com.ejomeau.prismamedia.manager.ProductManager
import com.ejomeau.prismamedia.manager.RoomManager
import com.ejomeau.prismamedia.model.Product
import com.ejomeau.prismamedia.ui.base.BaseViewModel
import com.ejomeau.prismamedia.ui.base.BaseViewState
import com.ejomeau.prismamedia.utils.subscribeOnIo
import com.ejomeau.prismamedia.utils.subscribeOnMain
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import org.koin.core.component.inject

class ListViewModel : BaseViewModel() {

    private val _products: MutableLiveData<MutableList<Pair<Product, Boolean>>> = MutableLiveData()
    val products: MutableLiveData<MutableList<Pair<Product, Boolean>>>
        get() = _products

    private val productManager: ProductManager by inject()

    private val roomManager: RoomManager by inject()

    fun getProducts() {
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
                    productManager.getProducts()
                        .subscribeBy(
                            onError = {
                                it.message?.apply {
                                    postViewState(BaseViewState.Error(this))
                                } ?: postViewState(BaseViewState.Error("Erreur"))
                            },
                            onSuccess = {
                                postViewState(BaseViewState.StopLoading)
                                _products.value?.clear()
                                val pair = ArrayList<Pair<Product, Boolean>>()
                                it.forEach { product ->
                                    pair.add(Pair(product, favourites.contains(product)))
                                }
                                _products.postValue(pair)
                            }
                        )
                }
            )
            .addTo(compositeDisposable)


    }

    fun onFavoriteChanged(state: Boolean, product: Product) {
        if (state)
            roomManager.insertProduct(product)
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
                    }
                )
                .addTo(compositeDisposable)
        else
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
                    }
                )
                .addTo(compositeDisposable)
    }
}