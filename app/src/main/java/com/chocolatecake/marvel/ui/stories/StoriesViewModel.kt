package com.chocolatecake.marvel.ui.stories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.domain.model.Story
import com.chocolatecake.marvel.ui.base.BaseViewModel
import com.chocolatecake.marvel.ui.core.listener.StoryListener
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
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
        _stories.postValue(Status.Loading)
        repository.refreshStories(limit = LIMIT).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({},{}).add()
        disposeObservableResponse(
            response = repository.getStories(),
            onSuccess = ::onStoriesSuccess,
            onFailure = ::onFailure,
        )
    }

    private fun onStoriesSuccess(status: Status<List<Story>>) {
        status.toData()?.let {
            _stories.postValue(Status.Success(it))
        } ?: _stories.postValue(Status.Failure("No Cashed Data"))
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