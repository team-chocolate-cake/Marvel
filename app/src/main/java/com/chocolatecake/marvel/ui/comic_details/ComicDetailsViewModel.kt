package com.chocolatecake.marvel.ui.comic_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel

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


    init {
        loadData()
    }

    fun loadData() {
        getCurrentComic()
        getCharactersOfComic()
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
            _characters.postValue(Status.Success(it))
        }
    }

    private fun onGetCharacterFailure(throwable: Throwable) {
        _toastMessage.postValue(ERROR_OCCURRED)
        _characters.postValue(Status.Failure(throwable.message.toString()))
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
