package com.ejomeau.prismamedia.utils

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Subscribe on io schedulers and observe on main thread schedulers.
 */


fun <T> Single<T>.subscribeOnIo(): Single<T> = this.subscribeOn(Schedulers.io())
fun <T> Observable<T>.subscribeOnIo(): Observable<T> = this.subscribeOn(Schedulers.io())
fun <T> Flowable<T>.subscribeOnIo(): Flowable<T> = this.subscribeOn(Schedulers.io())
fun Completable.subscribeOnIo(): Completable = this.subscribeOn(Schedulers.io())

fun <T> Single<T>.subscribeOnMain(): Single<T> = this.subscribeOn(AndroidSchedulers.mainThread())
fun <T> Observable<T>.subscribeOnMain(): Observable<T> = this.subscribeOn(AndroidSchedulers.mainThread())
fun <T> Flowable<T>.subscribeOnMain(): Flowable<T> = this.subscribeOn(AndroidSchedulers.mainThread())
fun Completable.subscribeOnMain(): Completable = this.subscribeOn(AndroidSchedulers.mainThread())


fun <T> Single<T>.observeOnIo(): Single<T> = this.observeOn(Schedulers.io())
fun <T> Observable<T>.observeOnIo(): Observable<T> = this.observeOn(Schedulers.io())
fun <T> Flowable<T>.observeOnIo(): Flowable<T> = this.observeOn(Schedulers.io())
fun Completable.observeOnIo(): Completable = this.observeOn(Schedulers.io())


fun <T : Any> T.toObservable(): Observable<T> = Observable.just(this)

fun <T : Any> T.toSingle(): Single<T> = Single.just(this)
