package com.chocolatecake.marvel.ui.comic_details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.comic_details.data.ComicDetailsItem

class ComicDetailsViewModel(

) : BaseViewModel(), ComicInteractionListener {

    private val repository by lazy {
        MarvelRepositoryImpl()
    }

    var currentComicId: Int = 1308

    private val _currentComic = MutableLiveData<Status<ComicsResult?>>()
    val currentComic: LiveData<Status<ComicsResult?>>
        get() = _currentComic

    private val _characters = MutableLiveData<Status<List<ProfileResult?>>>()
    val characters: LiveData<Status<List<ProfileResult?>>>
        get() = _characters

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    val itemsList = mutableListOf<ComicDetailsItem>()

//    init {
//        getCurrentComic()
//        getCharactersOfComic()
//        getEventsOfComic()
//    }

    fun loadData(id :Int){
        getCurrentComic(id)
        getCharactersOfComic(id)
        getEventsOfComic(id)
    }

    private fun getCurrentComic(currentComicId :Int) {
        _currentComic.postValue(Status.Loading)
        Log.i(TAG, "getCurrentComic: ")
        repository.getComicById(currentComicId)
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

    private fun getCharactersOfComic(currentComicId: Int) {
        repository.getCharactersForSeries(currentComicId)
            .subscribe(::onGetCharacterSuccess, ::onGetCharacterFailure)
            .add()
    }

    private fun onGetCharacterSuccess(status: Status<BaseResponse<ProfileResult>?>) {
        status.toData()?.data?.results?.let {
            Log.i(TAG, "onGetCharacterSuccess: ${it.toString()}")
            itemsList.add(ComicDetailsItem.Characters(it))
            _characters.postValue(Status.Success(it))
            
        }
    }

    private fun onGetCharacterFailure(throwable: Throwable) {
        _toastMessage.postValue(ERROR_OCCURRED)
        _characters.postValue(Status.Failure(throwable.message.toString()))
    }

    private fun getEventsOfComic(currentComicId: Int) {
        repository
            .getEventByComicId(currentComicId)
            .subscribe(::onGetEventsOfComicSuccess,::onGetEventsOfComicFailure)
            .add()
    }

    private fun onGetEventsOfComicSuccess(status:Status<BaseResponse<EventResult>?>){
        status.toData()?.data?.results?.let {list->
            Log.i(TAG, "onGetEventsOfComicSuccess: $list")
            list.forEach {
                itemsList.add(ComicDetailsItem.Events(it)) 
            }
        }
    }
    private fun onGetEventsOfComicFailure(throwable: Throwable){
        _toastMessage.postValue(ERROR_OCCURRED)
    }

    override fun onCharacterClick(characterId: Int?) {

    }

    override fun onEventClick(eventId: Int?) {

    }

    companion object {
        private const val ERROR_OCCURRED = "error occurred"
        private const val DATA_FETCHED_SUCCESSFULLY = "data fetched successfully"
        private const val TAG = "ComicDetailsViewModel"
    }
}
