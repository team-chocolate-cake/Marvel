package com.chocolatecake.marvel.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val repository = null // its type should be Repository,but it is not created
    protected val compositeDisposable = CompositeDisposable()
    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>
        get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _isLoading

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean>
        get() = _isSuccess

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}