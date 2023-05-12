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

class ComicDetailsViewModel(private val currentComicId: Int) :
    BaseViewModel(), ComicInteractionListener {

    private val repository by lazy {
        MarvelRepositoryImpl()
    }

    private val _currentComic = MutableLiveData<Status<ComicsResult>?>()
    val currentComic: LiveData<Status<ComicsResult>?>
        get() = _currentComic

    private val _characters = MutableLiveData<Status<List<ProfileResult>?>>()
    val characters: LiveData<Status<List<ProfileResult>?>>
        get() = _characters

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String>
        get() = _toastMessage

    private val _event = MutableLiveData<Status<List<EventResult>?>>()
    val event: LiveData<Status<List<EventResult>?>>
        get() = _event

    val itemsList = mutableListOf<ComicDetailsItem>()


    init {
        loadData()
    }

    fun loadData() {
        getCurrentComic()
        getCharactersOfComic()
        getEventsOfComic()
    }

    //region Current Comic
    private fun getCurrentComic() {
        _currentComic.postValue(Status.Loading)
        disposeResponse(
            response = repository.getComicById(currentComicId),
            onSuccess = :: onGetCurrentComicSuccess,
            onFailure = :: onGetCurrentComicFailure,
        )
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
    //endregion

    //region Characters of Comic
    private fun getCharactersOfComic() {
        _characters.postValue(Status.Loading)
        disposeResponse(
            response = repository.getCharactersForSeries(currentComicId),
            onSuccess = :: onGetCharacterSuccess,
            onFailure = :: onGetCharacterFailure,
        )
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
    //endregion

    //region Events of Comic
    private fun getEventsOfComic() {
        _event.postValue(Status.Loading)
        disposeResponse(
            response = repository.getEventByComicId(currentComicId),
            onSuccess = ::onGetEventsOfComicSuccess,
            onFailure = ::onGetEventsOfComicFailure,
        )
    }

    private fun onGetEventsOfComicSuccess(status: Status<List<EventResult>>) {
        status.toData()?.let { list ->
            list.forEach {
                itemsList.add(ComicDetailsItem.Events(it))
            }
        }
    }

    private fun onGetEventsOfComicFailure(throwable: Throwable) {
        _toastMessage.postValue(throwable.message)
    }
    //endregion


    override fun onClickCharacter(id: Int) {
        navigate(
            ComicDetailsFragmentDirections.actionComicsDetailsFragmentToCharacterDetailsFragment(
                id
            )
        )
    }

    override fun onClickEvent(id: Int) {
        navigate(
            ComicDetailsFragmentDirections.actionComicsDetailsFragmentToEventDetailsFragment(
                id
            )
        )
    }


    companion object {
        private const val ERROR_OCCURRED = "error occurred"
    }
}
