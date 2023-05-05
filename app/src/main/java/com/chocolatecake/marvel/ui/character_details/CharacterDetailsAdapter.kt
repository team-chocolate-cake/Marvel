package com.chocolatecake.marvel.ui.character_details

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.databinding.CharacterComicsBinding
import com.chocolatecake.marvel.databinding.CharacterHeaderBinding
import com.chocolatecake.marvel.ui.base.BaseAdapter

class CharacterDetailsAdapter(
    private val list: List<CharacterDetailsItem>,
    private val listener: CharacterDetailsListener,
) : BaseAdapter<CharacterDetailsItem>(list, listener) {
    override val layoutId: Int
        get() = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        Log.i("Created", "the list is ${list}")
        return when (viewType) {
            R.layout.character_header -> {
                Log.i("Created", "header")
                CharacterHeaderViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.character_header,
                        parent,
                        false
                    )
                )
            }

            R.layout.character_comics -> {
                Log.i("Created", "Comics")
                CharacterComicsViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.character_comics,
                        parent,
                        false
                    )
                )
            }

            else -> throw IllegalStateException("Unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        Log.i("Created", "The holder is ${holder::class.simpleName}")
        when (holder) {
            is CharacterHeaderViewHolder -> bindCharacterHeader(holder, position)
            is CharacterComicsViewHolder -> bindCharacterComics(holder, position)
        }

    }

    private fun bindCharacterHeader(holder: CharacterHeaderViewHolder, position: Int) {
        val header = list[position] as CharacterDetailsItem.Header
        holder.binding.item = header.characterInfo
    }

    private fun bindCharacterComics(holder: CharacterComicsViewHolder, position: Int) {
        val comics = list[position] as CharacterDetailsItem.Comics
        val adapter = CharacterComicsAdapter(comics.result, listener)
        holder.binding.recyclerViewComicsList.adapter = adapter
        holder.binding.item = comics
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is CharacterDetailsItem.Header -> R.layout.character_header
            is CharacterDetailsItem.Comics -> R.layout.character_comics
        }

    }

    inner class CharacterHeaderViewHolder(val binding: CharacterHeaderBinding) :
        BaseViewHolder(binding)

    inner class CharacterComicsViewHolder(val binding: CharacterComicsBinding) :
        BaseViewHolder(binding)
}



