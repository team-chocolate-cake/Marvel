package com.chocolatecake.marvel.ui.search.view

import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.databinding.FragmentSeacrhBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.search.SearchListAdapter
import com.chocolatecake.marvel.ui.search.model.SearchItemType.TYPE_CHARACTER
import com.chocolatecake.marvel.ui.search.model.SearchItemType.TYPE_COMICS
import com.chocolatecake.marvel.ui.search.model.SearchItemType.TYPE_SERIES
import com.chocolatecake.marvel.ui.search.model.SearchItems
import com.chocolatecake.marvel.ui.search.view_model.SearchViewModel
import com.chocolatecake.marvel.util.observeNonNull
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.core.Single

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSeacrhBinding, SearchViewModel>() {

    override val viewModel: SearchViewModel by viewModels()
    override val layoutIdFragment: Int
        get() = R.layout.fragment_seacrh

    private lateinit var adapter: SearchListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SearchListAdapter(viewModel)
        binding.recyclerView.adapter = adapter
        observeListItems()

        // Set up adapter for MaterialAutoCompleteTextView
        var adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_dropdown_item_1line, emptyList<String>())
        binding.editTextSearch.setAdapter(adapter)

        // Observe suggestions and update the adapter
        viewModel.searchHistory.observeNonNull(this) { searchHistory ->
            adapter.clear()
            adapter.addAll(searchHistory.map { it.keyword })
            adapter.notifyDataSetChanged()
        }
    }

    private fun observeListItems() {
        viewModel.state.observeNonNull(viewLifecycleOwner) {
            var count = 2
            if (it is Status.Success) {

                when (viewModel.searchType) {
                    TYPE_SERIES -> adapter.submitList(it.data.series.map { series ->
                        count = 2
                        SearchItems.SeriesItem(
                            series
                        )

                    })

                    TYPE_COMICS -> adapter.submitList(it.data.comics.map { comic ->
                        count = 2
                        SearchItems.ComicsItem(
                            comic
                        )
                    })

                    TYPE_CHARACTER -> adapter.submitList(it.data.characters.map { character ->
                        count = 3
                        SearchItems.CharacterItem(
                            character
                        )
                    })
                }
                binding.recyclerView.layoutManager = GridLayoutManager(
                    requireContext(),
                    count
                )
            }
        }
    }
}

//private fun <T> ArrayAdapter<T>.addAll(map: Single<List<T>>) {
//    return this.addAll(map)
//}

//private fun <T> ArrayAdapter<T>.addAll(searchHistory: Single<List<SearchHistory>>) {
//    this.addAll(searchHistory)
//}

