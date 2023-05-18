package com.chocolatecake.marvel.ui.stories_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto
import com.chocolatecake.marvel.data.remote.model.dto.StoryDto
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
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

    private val storyId: Int = savedStateHandle[STORY_ID] ?: 0

    private val _story = MutableLiveData<Status<StoryDto?>>()
    val story: LiveData<Status<StoryDto?>> = _story

    private val _creators = MutableLiveData<Status<List<ProfileDto>>>()
    val creators: MutableLiveData<Status<List<ProfileDto>>> get() = _creators

    private val _series = MutableLiveData<Status<List<SeriesDto>>>()
    val series: MutableLiveData<Status<List<SeriesDto>>> get() = _series

    private val _comics = MutableLiveData<Status<List<ComicDto>>>()
    val comics: MutableLiveData<Status<List<ComicDto>>> get() = _comics

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

    private fun onStorySuccess(status: Status<List<StoryDto>>) {
        status.toData()?.first().let {
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

    private fun onCreatorsSuccess(status: Status<List<ProfileDto>>) {
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

    private fun onSeriesSuccess(status: Status<List<SeriesDto>>) {
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

    private fun onComicsSuccess(status: Status<List<ComicDto>>) {
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

    private companion object{
        const val STORY_ID = "storyId"
    }
}