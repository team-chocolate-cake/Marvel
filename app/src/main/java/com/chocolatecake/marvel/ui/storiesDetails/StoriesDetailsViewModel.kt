package com.chocolatecake.marvel.ui.storiesDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.data.model.StoryResult
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.core.listener.ComicListener
import com.chocolatecake.marvel.ui.core.listener.CreatorsListener
import com.chocolatecake.marvel.ui.core.listener.SeriesListener

class StoriesDetailsViewModel(
    private val storyId: Int,
) : BaseViewModel(), CreatorsListener, ComicListener, SeriesListener {

    private val repository: MarvelRepository by lazy {
        MarvelRepositoryImpl()
    }

    private val _story = MutableLiveData<Status<StoryResult?>>()
    val story: LiveData<Status<StoryResult?>>
        get() = _story

    private val _creators = MutableLiveData<Status<List<ProfileResult>>>()
    val creators: MutableLiveData<Status<List<ProfileResult>>> get() = _creators

    private val _series = MutableLiveData<Status<List<SeriesResult>>>()
    val series: MutableLiveData<Status<List<SeriesResult>>> get() = _series

    private val _comics = MutableLiveData<Status<List<ComicsResult>>>()
    val comics: MutableLiveData<Status<List<ComicsResult>>> get() = _comics

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

    private fun onStorySuccess(status: Status<List<StoryResult>>) {
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

    private fun onCreatorsSuccess(status: Status<List<ProfileResult>>) {
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

    private fun onSeriesSuccess(status: Status<List<SeriesResult>>) {
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

    private fun onComicsSuccess(status: Status<List<ComicsResult>>) {
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