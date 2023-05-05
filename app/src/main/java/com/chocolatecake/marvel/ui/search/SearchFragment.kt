package com.chocolatecake.marvel.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.core.view.children
import androidx.core.view.forEachIndexed
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.util.Status
import com.chocolatecake.marvel.databinding.FragmentSeacrhBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

@Suppress("IMPLICIT_CAST_TO_ANY")
class SearchFragment() : BaseFragment<FragmentSeacrhBinding, SearchViewModel>() {
    override val viewModel: SearchViewModel by viewModels()
    override val layoutIdFragment: Int
        get() = R.layout.fragment_seacrh

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var currentList= mutableListOf<Any>()
        val adapter = SearchAdapter(currentList, viewModel)
        binding.recyclerView.adapter = adapter

        binding.chipGroup.setOnCheckedChangeListener { group, checkedIds ->
            val selectedChip = view.findViewById<Chip>(checkedIds)
            if (selectedChip != null) {
                val message = when (selectedChip.id) {
                    R.id.seriesChip -> {
                        viewModel.series.observe(viewLifecycleOwner){
                            it.toData()?.let { it1 -> adapter.updateList(it1)
                            }
                        }
                    }

                    R.id.comicsChip ->
                       viewModel.comics.observe(viewLifecycleOwner){
                          it.toData()?.let { it1 -> adapter.updateList(it1)
                          }
                           Log.e("Tag",it.toString())
                       }
                    R.id.characterChip -> viewModel.character.observe(viewLifecycleOwner){
                        it.toData()?.let { it1 -> adapter.updateList(it1)
                    }
                    }
                    else -> ""
                }

            }
        }
    }


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


}
