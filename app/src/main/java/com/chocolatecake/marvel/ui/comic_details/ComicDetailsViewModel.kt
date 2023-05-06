package com.chocolatecake.marvel.ui.comic_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.comic_details.data.ComicDetailsItem
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlin.math.log

class ComicDetailsViewModel : BaseViewModel(), ComicInteractionListener {

    private val repository by lazy {
        MarvelRepositoryImpl()
    }

    private val _currentComicId = MutableLiveData<Int?>()
    val currentComicId: LiveData<Int?>
        get() = _currentComicId

    private val _currentComic = MutableLiveData<Status<ComicsResult?>>()
    val currentComic: LiveData<Status<ComicsResult?>>
        get() = _currentComic

    private val _characters = MutableLiveData<Status<List<ProfileResult>?>>()
    val characters: LiveData<Status<List<ProfileResult>?>>
        get() = _characters

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    val itemsList = mutableListOf<ComicDetailsItem>()

    init {

        getCurrentComic()
        getCharactersOfComic()
    }

    fun updateCurrentComicId(id: Int) {
        Log.i(TAG, "updateCurrentComicId: ")
        _currentComicId.postValue(id)
    }

    private fun getCurrentComic() {
        repository.getComicById(1308)
            .subscribe(::onGetCurrentComicSuccess, this::onFailure)
            .add()
        Log.i(TAG, "getCurrentComic: else")
    }

    private fun onGetCurrentComicSuccess(status: Status<BaseResponse<ComicsResult>?>) {

        Log.i(TAG, "onGetCurrentComicSuccess: ")
        status.toData()?.data?.results?.first().let {
            Log.i(TAG, "onGetCurrentComicSuccess: ${it.toString()}")
            it?.let { it1 -> ComicDetailsItem.Header(it1) }
                ?.let { it2 -> itemsList.add(it2) }

            Log.i(TAG, "item list: ${itemsList.toString()}")
            
            _currentComic.postValue(Status.Success(it))

        }
    }

    private fun onFailure(throwable: Throwable) {
        Log.i(TAG, "onFailure: ${throwable.message} ")
        _toastMessage.postValue(ERROR_OCCURRED)
    }

    private fun getCharactersOfComic() {
        if (_currentComicId.value == null) return
        repository.getCharactersForSeries(_currentComicId.value!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(::onGetCharacterSuccess, ::onGetCharacterFailure)
            .add()
    }

    private fun onGetCharacterSuccess(status: Status<BaseResponse<SeriesResult>?>) {

    }

    private fun onGetCharacterFailure(throwable: Throwable) {

    }

    fun getEvents() {

    }

    override fun onCharacterClick(characterId: Int?) {

    }

    override fun onEventClick(eventId: Int?) {

    }

    companion object {
        private const val ERROR_OCCURRED = "error occurred"
        private const val TAG = "ComicDetailsViewModel"
    }
}