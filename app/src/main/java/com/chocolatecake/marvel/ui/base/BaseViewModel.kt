package com.chocolatecake.marvel.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.util.NavigationCommand
import com.chocolatecake.marvel.util.Event
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>>
        get() = _navigation

    fun navigate(navDirections: NavDirections) {
        _navigation.postValue(Event(NavigationCommand.ToDirection(navDirections)))
    }

    fun navigateBack() {
        _navigation.postValue(Event(NavigationCommand.Back))
    }


    fun <T : Any> disposeResponse(
        response: Single<Status<T>>,
        onSuccess: (data: Status<T>) -> Unit,
        onFailure: (e: Throwable) -> Unit,
    ) {
        response.subscribe(onSuccess, onFailure).add()
    }

    fun <T : Any> disposeObservableResponse(
        response: Observable<T>,
        onSuccess: (data: T) -> Unit,
        onFailure: (e: Throwable) -> Unit,
    ) {
        response.subscribe(onSuccess, onFailure).add()
    }

    fun Disposable.add() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}