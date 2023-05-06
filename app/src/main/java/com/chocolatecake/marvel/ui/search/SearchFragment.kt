package com.chocolatecake.marvel.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.base.SearchResult
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.databinding.FragmentSeacrhBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.google.android.material.chip.Chip

@Suppress("IMPLICIT_CAST_TO_ANY")
class SearchFragment() : BaseFragment<FragmentSeacrhBinding, SearchViewModel>() {
    override val viewModel: SearchViewModel by viewModels()
    override val layoutIdFragment: Int
        get() = R.layout.fragment_seacrh

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentList = mutableListOf<SearchResult>()
        val adapter = SearchAdapter(currentList, viewModel)
        binding.recyclerView.adapter = adapter

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedChip = view.findViewById<Chip>(checkedId)
            selectedChip?.let { chip ->
                when (chip.id) {
                    R.id.seriesChip -> {
                        viewModel.series.observe(viewLifecycleOwner) { seriesResultList ->
                            val searchResults = seriesResultList.toData().let { it as? List<SearchResult>  }
                            if (searchResults != null) {
                                adapter.updateList(searchResults)
                            }
                        }
                    }
                    R.id.comicsChip -> {
                        viewModel.comics.observe(viewLifecycleOwner) { comicsResultList ->
                            val searchResults = comicsResultList?.toData()?.let { it as? List<SearchResult> }
                            searchResults?.let {
                                adapter.updateList(it)
                                Log.d("update", it.toString())
                            }
                        }
                    }
                    R.id.characterChip -> {
                        viewModel.character.observe(viewLifecycleOwner) { profileResultList ->
                            val searchResults = profileResultList?.toData()?.let { it as? List<SearchResult> }
                            searchResults?.let {
                                adapter.updateList(it)
                            }
                        }
                    }
                }
            }
        }
    }


    /*

    //    private fun addSearchCallBack() {
    //        Observable.create { emitter ->
    //            binding.editTextSearch.doOnTextChanged{ text, start, before, count ->
    //                emitter.onNext(text.toString())
    //            }
    //        }.debounce(500, TimeUnit.MILLISECONDS).subscribe { t ->
    //            searchQuery = searchQuery.copy(
    //                title = t,
    //                status = getSelectedChips()
    //            )
    //            applySearch()
    //        }.add(compositeDisposable)
    //    }

        private fun applySearch() {
            viewModel.series
        }

    //    private fun getSelectedChips(): List<SearchItemType> {
    //        val statusList = mutableListOf<SearchItemType>()
    //        binding.chipGroup.forEachIndexed { index, view ->
    //            if ((view as Chip).isChecked) {
    //                statusList.add(SearchItemType.createStatus(index))
    //            }
    //        }
    //        return statusList.toList()
    //    }

        private fun onNext() {

        }

        private fun onError() {

        }

    */
}
