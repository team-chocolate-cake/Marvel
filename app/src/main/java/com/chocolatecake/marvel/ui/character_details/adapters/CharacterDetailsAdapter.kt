package com.chocolatecake.marvel.ui.character_details.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.databinding.CharacterComicsBinding
import com.chocolatecake.marvel.databinding.CharacterHeaderBinding
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.character_details.CharacterDetailsItem
import com.chocolatecake.marvel.ui.comic.ComicListener

class CharacterDetailsAdapter(
    private val itemsList: List<CharacterDetailsItem>,
    private val listener: ComicListener,
) : BaseAdapter<CharacterDetailsItem>(itemsList, listener) {
    override val layoutId: Int = R.layout.character_details

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            HEADER -> {
                CharacterHeaderViewHolder(
                    DataBindingUtil.inflate(
                        LayoutInflater.from(parent.context),
                        R.layout.character_header,
                        parent,
                        false
                    )
                )
            }

            COMICS_SECTION -> {
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
        when (holder) {
            is CharacterHeaderViewHolder -> bindCharacterHeader(holder, position)
            is CharacterComicsViewHolder -> bindCharacterComics(holder, position)
        }
    }

    private fun bindCharacterHeader(holder: CharacterHeaderViewHolder, position: Int) {
        val header = itemsList[position] as CharacterDetailsItem.Header
        holder.binding.item = header.characterInfo
    }

    private fun bindCharacterComics(holder: CharacterComicsViewHolder, position: Int) {
        val comics = itemsList[position] as CharacterDetailsItem.Comics
        val adapter = CharacterComicsAdapter(comics.result as List<ComicsResult>, listener)
        holder.binding.recyclerViewComicsList.adapter = adapter
        holder.binding.item = comics
    }

    override fun getItemViewType(position: Int): Int {
        return when (itemsList[position]) {
            is CharacterDetailsItem.Header -> HEADER
            is CharacterDetailsItem.Comics -> COMICS_SECTION
        }
    }

    class CharacterHeaderViewHolder(val binding: CharacterHeaderBinding) :
        BaseViewHolder(binding)

    class CharacterComicsViewHolder(val binding: CharacterComicsBinding) :
        BaseViewHolder(binding)

    companion object {
        const val HEADER = 0
        const val COMICS_SECTION = 1
    }
}

