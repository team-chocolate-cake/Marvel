package com.chocolatecake.marvel.ui.stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.domain.model.Story
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.core.listener.StoryListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoriesViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel(), StoryListener {

    private val _stories = MutableLiveData<Status<List<Story>>>()
    val stories: LiveData<Status<List<Story>>>
        get() = _stories


    init {
        loadData()
    }

    fun loadData() {
        getStories()
    }

    //region Stories
    private fun getStories() {
        repository.refreshStories()
        _stories.postValue(Status.Loading)
        disposeObservableResponse(
            response = repository.getStories(limit = LIMIT),
            onSuccess = ::onStoriesSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onStoriesSuccess(status: Status<List<Story>>) {
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