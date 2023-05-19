package com.chocolatecake.marvel.domain.mapper.search_history

import com.chocolatecake.marvel.data.local.entities.SearchHistoryEntity
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.SearchHistory
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SearchHistoryUIMapper @Inject constructor() : Mapper<SearchHistoryEntity, SearchHistory> {
    override fun map(input: SearchHistoryEntity): SearchHistory {
        return SearchHistory(
            id = input.id,
            keyword = input.keyword,
            type = input.type,
        )
    }
}