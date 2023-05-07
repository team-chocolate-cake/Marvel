package com.chocolatecake.marvel.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentSeacrhBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.google.android.material.chip.Chip

class SearchFragment : BaseFragment<FragmentSeacrhBinding, SearchViewModel>() {
    override val viewModel: SearchViewModel by viewModels()
    override val layoutIdFragment: Int
        get() = R.layout.fragment_seacrh
    lateinit var adapter: SearchListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SearchListAdapter(viewModel)
        binding.recyclerView.adapter = adapter
        updateItems()

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedChip = view.findViewById<Chip>(checkedId)
            selectedChip?.let { chip ->
                when (chip.id) {
                    R.id.seriesChip -> {
                        viewModel.filterSeries()
                    }

                    R.id.comicsChip -> {
                        viewModel.filterComics()
                    }

                    R.id.characterChip -> {
                        viewModel.filterCharacters()
                    }
                }
            }
        }
    }

    private fun updateItems() {
        viewModel.series.observe(viewLifecycleOwner){
            it.toData()?.let {
                adapter.submitList(it.map { SearchItems.SeriesItem(it) })
            }
        }
        viewModel.comics.observe(viewLifecycleOwner){
            it.toData()?.let {
                adapter.submitList(it.map { SearchItems.ComicsItem(it) })
            }
        }
        viewModel.character.observe(viewLifecycleOwner){
            it.toData()?.let {
                adapter.submitList(it.map { SearchItems.CharacterItem(it) })
                Log.d("123123123", "updateItems: $it")
            }
        }
    }



//        private fun addSearchCallBack() {
//            Observable.create { emitter ->
//                binding.editTextSearch.doOnTextChanged{ text, start, before, count ->
//                    emitter.onNext(text.toString())
//                }
//            }.debounce(500, TimeUnit.MILLISECONDS).subscribe { t ->
//                searchQuery = searchQuery.copy(
//                    title = t,
//                    status = getSelectedChips()
//                )
//                applySearch()
//            }.add(compositeDisposable)
//        }

//        private fun applySearch() {
//            viewModel.series
//        }
//
//        private fun getSelectedChips(): List<SearchItemType> {
//            val statusList = mutableListOf<SearchItemType>()
//            binding.chipGroup.forEachIndexed { index, view ->
//                if ((view as Chip).isChecked) {
//                    statusList.add(SearchItemType.createStatus(index))
//                }
//            }
//            return statusList.toList()
//        }
//
//        private fun onNext() {
//
//        }
//
//        private fun onError() {
//
//        }


}
