package com.chocolatecake.marvel.ui.search.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.databinding.FragmentSeacrhBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.chocolatecake.marvel.ui.search.model.SearchItemType.*
import com.chocolatecake.marvel.ui.search.model.SearchItems
import com.chocolatecake.marvel.ui.search.SearchListAdapter
import com.chocolatecake.marvel.ui.search.view_model.SearchViewModel

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
    }

    private fun observeListItems() {
        viewModel.state.observe(viewLifecycleOwner) {
            if (it is Status.Success) {
                when (viewModel.searchType) {
                    TYPE_SERIES -> adapter.submitList(it.data.series.map { series ->
                        SearchItems.SeriesItem(
                            series
                        )
                    })

                    TYPE_COMICS -> adapter.submitList(it.data.comics.map { comic ->
                        SearchItems.ComicsItem(
                            comic
                        )
                    })

                    TYPE_CHARACTER -> adapter.submitList(it.data.characters.map { character ->
                        SearchItems.CharacterItem(
                            character
                        )
                    })
                }
            }
        }
    }
}
