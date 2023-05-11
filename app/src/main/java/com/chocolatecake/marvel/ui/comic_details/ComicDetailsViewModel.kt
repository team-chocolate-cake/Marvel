package com.chocolatecake.marvel.ui.comic_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.comic_details.data.ComicDetailsItem

class ComicDetailsViewModel: BaseViewModel(), ComicInteractionListener {

    private val repository by lazy {
        MarvelRepositoryImpl()
    }
    private var currentComicId: Int = 1308
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

    init {
       loadData()
    }

    fun loadData(){
        getCurrentComic()
        getCharactersOfComic()
        getEventsOfComic()
    }

    private fun getCurrentComic() {
        _currentComic.postValue(Status.Loading)
        repository.getComicById(currentComicId)
            .subscribe(::onGetCurrentComicSuccess, ::onGetCurrentComicFailure)
            .add()
    }

    private fun onGetCurrentComicSuccess(status: Status<List<ComicsResult>>) {
        status.toData()?.first()?.let {
            itemsList.add(ComicDetailsItem.Header(it))
            _currentComic.postValue(Status.Success(it))
            
        }
    }

    private fun onGetCurrentComicFailure(throwable: Throwable) {
        _currentComic.postValue(Status.Failure(throwable.message.toString()))
        _toastMessage.postValue(ERROR_OCCURRED)
    }

    private fun getCharactersOfComic() {
        repository.getCharactersForSeries(currentComicId)
            .subscribe(::onGetCharacterSuccess, ::onGetCharacterFailure)
            .add()
    }

    private fun onGetCharacterSuccess(status: Status<List<ProfileResult>>) {
        status.toData()?.let {
            itemsList.add(ComicDetailsItem.Characters(it))
            _characters.postValue(Status.Success(it))
            
        }
    }

    private fun onGetCharacterFailure(throwable: Throwable) {
        _toastMessage.postValue(ERROR_OCCURRED)
        _characters.postValue(Status.Failure(throwable.message.toString()))
    }

    private fun getEventsOfComic() {
        repository
            .getEventByComicId(currentComicId)
            .subscribe(::onGetEventsOfComicSuccess,::onGetEventsOfComicFailure)
            .add()
    }

    private fun onGetEventsOfComicSuccess(status:Status<List<EventResult>>){
        status.toData()?.let {list->
            list.forEach {
                itemsList.add(ComicDetailsItem.Events(it)) 
            }
        }
    }
    private fun onGetEventsOfComicFailure(throwable: Throwable){
        _toastMessage.postValue(throwable.message)
    }

    override fun onClickCharacter(id: Int) {
//        TODO("Not yet implemented")
    }

    override fun onClickEvent(id: Int) {
//        TODO("Not yet implemented")
    }

    companion object {
        private const val ERROR_OCCURRED = "error occurred"
    }
}
