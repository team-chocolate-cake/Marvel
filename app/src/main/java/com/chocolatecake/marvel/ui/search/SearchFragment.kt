package com.chocolatecake.marvel.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentSeacrhBinding
import com.chocolatecake.marvel.ui.base.BaseFragment

class SearchFragment : BaseFragment<FragmentSeacrhBinding, SearchViewModel>() {
    override val viewModel: SearchViewModel by viewModels()
    override val layoutIdFragment: Int
        get() = R.layout.fragment_seacrh

    lateinit var adapter: SearchListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SearchListAdapter(viewModel)
        binding.recyclerView.adapter = adapter
        observeListItems()
    }

    private fun observeListItems() {
        viewModel.series.observe(viewLifecycleOwner) { listStatus ->
            listStatus.toData()?.let { seriesResults ->
                adapter.submitList(seriesResults.map { SearchItems.SeriesItem(it) })
            }
        }
        viewModel.comics.observe(viewLifecycleOwner) { listStatus ->
            listStatus.toData()?.let { comicsResults ->
                adapter.submitList(comicsResults.map { SearchItems.ComicsItem(it) })
            }
        }
        viewModel.character.observe(viewLifecycleOwner) { listStatus ->
            listStatus.toData()?.let { profileResults ->
                adapter.submitList(profileResults.map { SearchItems.CharacterItem(it) })
            }
        }
    }
}
