package com.chocolatecake.marvel.ui.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.FragmentSeacrhBinding
import com.chocolatecake.marvel.ui.base.BaseFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class SearchFragment(
    override val viewModelClass: Class<SearchViewModel>,
    override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSeacrhBinding,
    override val layoutIdFragment: Int
) : BaseFragment<FragmentSeacrhBinding,SearchViewModel>() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = inflater.inflate(R.layout.fragment_seacrh, container, false)
            val chipGroup = view.findViewById<ChipGroup>(R.id.chipGroup)
            chipGroup.setOnCheckedChangeListener { group, checkedId ->
                val selectedChip = view.findViewById<Chip>(checkedId)
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
            return view
        }
    }
