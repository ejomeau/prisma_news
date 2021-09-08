package com.ejomeau.prismamedia.koin

import com.ejomeau.prismamedia.ui.favourites.FavouritesViewModel
import com.ejomeau.prismamedia.ui.list.ListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * ViewModelsModule
 *
 * Contains viewmodel instances
 */
val viewModelsModule = module {

    viewModel { ListViewModel() }
    viewModel { FavouritesViewModel() }
}