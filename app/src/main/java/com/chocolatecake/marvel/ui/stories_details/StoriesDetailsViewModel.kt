package com.chocolatecake.marvel.ui.stories_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.domain.model.Creator
import com.chocolatecake.marvel.domain.model.Series
import com.chocolatecake.marvel.domain.model.StoryDetails
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.core.listener.ComicListener
import com.chocolatecake.marvel.ui.core.listener.CreatorsListener
import com.chocolatecake.marvel.ui.core.listener.SeriesListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoriesDetailsViewModel @Inject constructor(
    private val repository: MarvelRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(), CreatorsListener, ComicListener, SeriesListener {

    private val storyId =
        StoriesDetailsFragmentArgs.fromSavedStateHandle(savedStateHandle).storyId

    private val _story = MutableLiveData<Status<StoryDetails>>()
    val story: LiveData<Status<StoryDetails>> = _story

    private val _creators = MutableLiveData<Status<List<Creator>>>()
    val creators: MutableLiveData<Status<List<Creator>>> get() = _creators

    private val _series = MutableLiveData<Status<List<Series>>>()
    val series: MutableLiveData<Status<List<Series>>> get() = _series

    private val _comics = MutableLiveData<Status<List<Comic>>>()
    val comics: MutableLiveData<Status<List<Comic>>> get() = _comics

    init {
        loadData()
    }

    fun loadData() {
        getComicsByStoryId()
        getSeriesByStoryId()
        getCreatorsByStoryId()
        getStoryById()
    }

    //region Story
    private fun getStoryById() {
        _story.postValue(Status.Loading)
        disposeResponse(
            response = repository.getStoryById(storyId),
            onSuccess = ::onStorySuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onStorySuccess(status: Status<StoryDetails>) {
        status.toData()?.let {
            _story.postValue(Status.Success(it))
        }
    }
    //endregion

    //region Creators
    private fun getCreatorsByStoryId() {
        _creators.postValue(Status.Loading)
        disposeResponse(
            response = repository.getCreatorsByStoryId(storyId),
            onSuccess = ::onCreatorsSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onCreatorsSuccess(status: Status<List<Creator>>) {
        status.toData()?.let {
            _creators.postValue(Status.Success(it))
        }
    }
    //endregion

    //region Series
    private fun getSeriesByStoryId() {
        _series.postValue(Status.Loading)
        disposeResponse(
            response = repository.getSeriesByStoryId(storyId),
            onSuccess = ::onSeriesSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onSeriesSuccess(status: Status<List<Series>>) {
        status.toData()?.let {
            _series.postValue(Status.Success(it))
        }
    }
    //endregion

    //region Comics
    private fun getComicsByStoryId() {
        _comics.postValue(Status.Loading)
        disposeResponse(
            response = repository.getComicsByStoryId(storyId),
            onSuccess = ::onComicsSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onComicsSuccess(status: Status<List<Comic>>) {
        status.toData()?.let {
            _comics.postValue(Status.Success(it))
        }
    }
    //endregion

    private fun onFailure(throwable: Throwable) {
        _series.postValue(Status.Failure(throwable.message.toString()))
        _comics.postValue(Status.Failure(throwable.message.toString()))
        _creators.postValue(Status.Failure(throwable.message.toString()))
    }


    override fun onClickComic(id: Int) {
        navigate(
            StoriesDetailsFragmentDirections.actionStoriesDetailsFragmentToComicsDetailsFragment(
                id
            )
        )
    }

    override fun onClickCreator(id: Int) {
        navigate(
            StoriesDetailsFragmentDirections.actionStoriesDetailsFragmentToCreatorDetailsFragment(
                id
            )
        )
    }

    override fun onClickSeries(id: Int) {
        navigate(
            StoriesDetailsFragmentDirections.actionStoriesDetailsFragmentToSeriesDetailsFragment(
                id
            )
        )
    }
}