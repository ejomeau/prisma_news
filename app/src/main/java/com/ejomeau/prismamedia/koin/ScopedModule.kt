package com.ejomeau.prismamedia.koin

import com.ejomeau.prismamedia.manager.ProductManager
import com.ejomeau.prismamedia.manager.RoomManager
import com.ejomeau.prismamedia.manager.impl.ProductManagerImpl
import com.ejomeau.prismamedia.manager.impl.RoomManagerImpl
import com.ejomeau.prismamedia.retrofit.RetrofitClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val scopedModule = module {

    //factory : new declaration each time
    factory<ProductManager> { ProductManagerImpl() }
    factory { RetrofitClient() }
    single<RoomManager>  { RoomManagerImpl(this.androidContext())}


/*    scope<ListViewModel> {
        scoped<ProductManager> { ProductManagerImpl() }
    }*/

}