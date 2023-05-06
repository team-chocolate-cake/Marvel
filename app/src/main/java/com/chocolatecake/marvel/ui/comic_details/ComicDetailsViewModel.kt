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

    private val _characters = MutableLiveData<Status<List<SeriesResult?>>>()
    val characters: LiveData<Status<List<SeriesResult?>>>
        get() = _characters

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    val itemsList = mutableListOf<ComicDetailsItem>()

    init {
        Log.i(TAG, "view model ")
        getCurrentComic()
        getCharactersOfComic()
    }

    fun updateCurrentComicId(id: Int) {
        _currentComicId.postValue(id)
    }

    private fun getCurrentComic() {
        _currentComic.postValue(Status.Loading)
        Log.i(TAG, "getCurrentComic: ")
        repository.getComicById(1308)
            .subscribe(::onGetCurrentComicSuccess, ::onGetCurrentComicFailure)
            .add()
    }

    private fun onGetCurrentComicSuccess(status: Status<BaseResponse<ComicsResult>?>) {
        status.toData()?.data?.results?.first()?.let {
            Log.i(TAG, "onGetCurrentComicSuccess: ${it.toString()}")
            itemsList.add(ComicDetailsItem.Header(it))
            _currentComic.postValue(Status.Success(it))
        }
    }

    private fun onGetCurrentComicFailure(throwable: Throwable) {
        _currentComic.postValue(Status.Failure(throwable.message.toString()))
        _toastMessage.postValue(ERROR_OCCURRED)
    }

    private fun getCharactersOfComic() {
        repository.getCharactersForSeries(1308)
            .subscribe(::onGetCharacterSuccess, ::onGetCharacterFailure)
            .add()
    }

    private fun onGetCharacterSuccess(status: Status<BaseResponse<SeriesResult>?>) {
        status.toData()?.data?.results?.let {
            itemsList.add(ComicDetailsItem.Characters(it))
            _characters.postValue(Status.Success(it))
        }
    }

    private fun onGetCharacterFailure(throwable: Throwable) {
        _toastMessage.postValue(ERROR_OCCURRED)
        _characters.postValue(Status.Failure(throwable.message.toString()))
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