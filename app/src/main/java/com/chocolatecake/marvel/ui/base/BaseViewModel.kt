package com.chocolatecake.marvel.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.chocolatecake.marvel.util.Event
import com.chocolatecake.marvel.util.NavigationCommand
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
     val navigation: LiveData<Event<NavigationCommand>> get() = _navigation

     fun navigate(navDirections: NavDirections) {
        _navigation.value = Event(NavigationCommand.ToDirection(navDirections))
    }

     fun navigateBack() {
        _navigation.value = Event(NavigationCommand.Back)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    protected fun Disposable.add(){
        compositeDisposable.add(this)
    }
}