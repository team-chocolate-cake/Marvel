package com.chocolatecake.marvel.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentSeacrhBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.google.android.material.chip.Chip

class SearchFragment() : BaseFragment<FragmentSeacrhBinding, SearchViewModel>() {
    override val viewModel: SearchViewModel by viewModels()
    override val layoutIdFragment: Int
        get() = R.layout.fragment_seacrh
    lateinit var adapter: SearchAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = SearchAdapter(
            mutableListOf(
                SearchItems.SeriesItem(null),
                SearchItems.ComicsItem(null),
                SearchItems.CharacterItem(null),
            ), viewModel
        )
        binding.recyclerView.adapter = adapter
        updateItems()
//        handleClickChips()

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedChip = view.findViewById<Chip>(checkedId)
            selectedChip?.let { chip ->
                when (chip.id) {
                    R.id.seriesChip -> {
                        viewModel.isSeriesSelected.observe(viewLifecycleOwner) {
                            viewModel.getAllSeries()
                            binding.recyclerView.adapter?.notifyDataSetChanged()
                            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
                        }
                    }

                    R.id.comicsChip -> {
                        viewModel.isComicSelected.observe(viewLifecycleOwner){
                            viewModel.getAllComics()
                            binding.recyclerView.adapter?.notifyDataSetChanged()
                            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()

                        }
                    }


                    R.id.characterChip -> {
                        viewModel.isCharterSelected.observe(viewLifecycleOwner) {
                            viewModel.getAllCharacters()
                            binding.recyclerView.adapter?.notifyDataSetChanged()
                            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            }
        }
    }

    private fun updateItems() {
        viewModel.series.observe(viewLifecycleOwner){
            it.toData()?.let {
                it.map {
                    adapter.updateList(SearchItems.SeriesItem(it))
                }
            }
        }
        viewModel.comics.observe(viewLifecycleOwner){
            it.toData()?.let {
                it.map {
                    adapter.updateList(SearchItems.ComicsItem(it))
                }
            }
        }
        viewModel.character.observe(viewLifecycleOwner){
            it.toData()?.let {
                it.map {
                    adapter.updateList(SearchItems.CharacterItem(it))
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleClickChips() {
        viewModel.isSeriesSelected.observe(viewLifecycleOwner){
            viewModel.getAllSeries()
            binding.recyclerView.adapter?.notifyDataSetChanged()
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()
        }
        viewModel.isComicSelected.observe(viewLifecycleOwner){
            viewModel.getAllComics()
            binding.recyclerView.adapter?.notifyDataSetChanged()
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()

        }
        viewModel.isCharterSelected.observe(viewLifecycleOwner){
            viewModel.getAllCharacters()
            binding.recyclerView.adapter?.notifyDataSetChanged()
            Toast.makeText(requireContext(), "$it", Toast.LENGTH_SHORT).show()

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
