package com.chocolatecake.marvel.ui.creator_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.domain.model.CreatorDetails
import com.chocolatecake.marvel.domain.model.Series
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.creator_details.adapter.CreatorDetailsListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatorDetailsViewModel @Inject constructor(
    private val repository: MarvelRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(), CreatorDetailsListener {

    private val creatorId: Int = savedStateHandle[CREATOR_ID] ?: 0

    private val _comicsList = MutableLiveData<Status<List<Comic>?>>()
    val comicsList: LiveData<Status<List<Comic>?>> = _comicsList

    private val _seriesList = MutableLiveData<Status<List<Series>?>>()
    val seriesList: LiveData<Status<List<Series>?>> = _seriesList

    private val _creator = MutableLiveData<Status<List<CreatorDetails>?>>()
    val creator: LiveData<Status<List<CreatorDetails>?>> = _creator

    init {
        loadData()
    }

    fun loadData() {
        getCreator()
        getSeries()
        getComics()
    }

    //region Creator
    private fun getCreator() {
        _creator.postValue(Status.Loading)
        disposeResponse(
            response = repository.getCreatorById(creatorId),
            onSuccess = ::onGetCreatorSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onGetCreatorSuccess(status: Status<List<CreatorDetails>>) {
        status.toData()?.let { _creator.postValue(Status.Success(it)) }
    }
    //endregion

    //region Series
    private fun getSeries() {
        _seriesList.postValue(Status.Loading)
        disposeResponse(
            response = repository.getSeriesForCreator(creatorId),
            onSuccess = ::onGetSeriesSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onGetSeriesSuccess(status: Status<List<Series>>) {
        status.toData()?.let { _seriesList.postValue(Status.Success(it)) }
    }
    //endregion

    //region Comics
    private fun getComics() {
        _comicsList.postValue(Status.Loading)
        disposeResponse(
            response = repository.getComicsForCreator(creatorId),
            onSuccess = ::onGetComicsSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onGetComicsSuccess(status: Status<List<Comic>>) {
        status.toData()?.let { _comicsList.postValue(Status.Success(it)) }
    }
    //endregion

    private fun onFailure(throwable: Throwable) {
        _creator.postValue(Status.Failure(throwable.message.toString()))
        _seriesList.postValue(Status.Failure(throwable.message.toString()))
        _comicsList.postValue(Status.Failure(throwable.message.toString()))
    }


    override fun onClickSeries(id: Int) {
        navigate(CreatorDetailsFragmentDirections.actionCreatorDetailsFragmentToSeriesDetailsFragment(id))
    }

    override fun onClickComic(id: Int) {
        navigate(CreatorDetailsFragmentDirections.actionCreatorDetailsFragmentToComicsDetailsFragment(id))
    }

    private companion object{
        const val CREATOR_ID = "creatorId"
    }
}