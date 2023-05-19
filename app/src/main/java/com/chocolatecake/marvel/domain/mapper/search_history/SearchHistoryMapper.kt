package com.chocolatecake.marvel.domain.mapper.search_history

import com.chocolatecake.marvel.data.local.entities.SearchHistoryEntity
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.SearchHistory
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SearchHistoryMapper @Inject constructor() : Mapper<SearchHistory,SearchHistoryEntity> {
    override fun map(input: SearchHistory): SearchHistoryEntity {
        return SearchHistoryEntity(
            keyword = input.keyword,
            type = input.type,
            id = 0,
        )
    }
}