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
import java.util.concurrent.TimeUnit

class SearchFragment(
    override val viewModelClass: Class<SearchViewModel>,
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSeacrhBinding,
    override val layoutIdFragment: Int
) : BaseFragment<FragmentSeacrhBinding,SearchViewModel>() {
    private var searchQuery=SearchQuery()

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

    @SuppressLint("CheckResult")
    private fun addSearchCallBacks(){
        Observable.create{ emitter ->
            binding.editTextSearch.addTextChangedListener { text ->
            searchQuery = searchQuery.copy(text.toString(),getSelectedChips())
             emitter.onNext(searchQuery)
            }
        }.debounce(500,TimeUnit.MILLISECONDS).subscribe{

        }
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
    private fun onError(){}
    private fun setupChip() {
        binding.characterChip.setOnClickListener {
            Log.e("banan","characterChip")
        }
        binding.comicsChip.setOnClickListener {
            Log.e("banan","comicsChip")
        }
        binding.seriesChip.setOnClickListener {
            Log.e("banan","seriesChip")
        }
    }
}
