package com.chocolatecake.marvel.ui.stories.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.model.StoryResult
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.core.listener.StoryListener
import com.chocolatecake.marvel.ui.search.view.SearchFragmentDirections
import com.chocolatecake.marvel.ui.stories.view.StoriesFragmentDirections

class StoriesViewModel : BaseViewModel(), StoryListener {

    val repository: MarvelRepository by lazy { MarvelRepositoryImpl() }

    private val _stories = MutableLiveData<Status<List<StoryResult>>>()
    val stories: LiveData<Status<List<StoryResult>>>
        get() = _stories


    init {
        loadData()
    }

    fun loadData() {
        getStories()
    }

    //region Stories
    private fun getStories() {
        _stories.postValue(Status.Loading)
        disposeResponse(
            response = repository.getStories(limit = LIMIT),
            onSuccess = ::onStoriesSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onStoriesSuccess(status: Status<List<StoryResult>>) {
        status.toData()?.let {
            _stories.postValue(Status.Success(it))
        }
    }

    private fun onFailure(throwable: Throwable) {
        _stories.postValue(Status.Failure(throwable.message.toString()))
    }
    //endregion

    override fun onClickStory(id: Int) {
        navigate(StoriesFragmentDirections.actionStoriesFragmentToStoriesDetailsFragment(id))
    }


    private companion object {
        const val LIMIT = 30
    }
}