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
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentSeacrhBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit

class SearchFragment() : BaseFragment<FragmentSeacrhBinding,SearchViewModel>() {
    private var searchQuery=SearchQuery()
    protected val compositeDisposable = CompositeDisposable()
    lateinit var adapter:SearchAdapter

    override val viewModelClass: Class<SearchViewModel>
        get() =SearchViewModel::class.java
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSeacrhBinding
        get() = FragmentSeacrhBinding::inflate
    override val layoutIdFragment: Int
        get() = R.layout.fragment_seacrh

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
              binding.chipGroup.setOnCheckedChangeListener { group, checkedIds ->
                val selectedChip = view.findViewById<Chip>(checkedIds)
                if (selectedChip != null) {
                    val message = when(selectedChip.id) {
                        R.id.seriesChip ->{Log.e("banan","Series selected")  }
                        R.id.comicsChip -> "Comics selected"
                        R.id.characterChip -> "Character selected"
                        else -> ""
                    }
                    Log.e("banan",message.toString())
                }
            }
    }

    private fun addSearchCallBacks(){
        Observable.create{ emitter ->
            binding.editTextSearch.addTextChangedListener { text ->
             emitter.onNext(searchQuery)
            }
        }.debounce(500,TimeUnit.MILLISECONDS).subscribe{
            searchQuery = searchQuery.copy(it.toString(),getSelectedChips())
            applySearch()
        }.add()
    }

    private fun Disposable.add(){
        compositeDisposable.add(this)
    }

    private fun applySearch() {
       viewModel.series
    }

    private fun getSelectedChips(): List<SearchItemType> {
        val statusList = mutableListOf<SearchItemType>()
        binding.chipGroup.forEachIndexed { index, view ->
            if ((view as Chip).isChecked) {
                statusList.add(SearchItemType.createStatus(index))
            }
        }
        return statusList.toList()
    }

    private fun onNext(){

    }
    private fun onError(){

    }

    private fun setupChip() {
        var items = mutableListOf<SearchType>()

        binding.characterChip.setOnClickListener {
            Log.e("banan","characterChip")
            viewModel.character.observe(viewLifecycleOwner){
             val listCharacter=it.toData()
            items.add(SearchType.CharacterItem())
                adapter= SearchAdapter(,viewModel)
            }
        }
        binding.comicsChip.setOnClickListener {
            Log.e("banan","comicsChip")
        }
        binding.seriesChip.setOnClickListener {
            Log.e("banan","seriesChip")
        }
    }
}
